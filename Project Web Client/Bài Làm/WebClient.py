import socket
import re
import os
import pathlib				#Get the current directory
import shutil
import threading
from time import sleep

#Functions

def Get_Directory():
	current_dir = str(pathlib.Path(__file__).parent.resolve())
	if '/' in current_dir:
		dir_divider = '/'
	elif '\\' in current_dir:
		dir_divider = '\\'
	return current_dir , dir_divider

def Connect(domain):
	client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	client.connect((domain,Port))
	return client

def Handle_Lost_Connection(sock):
	sock.close()
	print("Lost connection to server!!!")
	print("Trying to reconnect...")
	connect = False
	while not connect:
		try:
			sock = Connect()
			connect = True
			print('Successfully connected to the server!!!')
		except socket.error:
			sleep(2)

def Send_Request(sock,domain,subdomain):
	try:
		req_cmd = f'GET /{subdomain} HTTP/1.1\r\nHost:{domain}\r\nAccept: text/html,image/gif,image/jpeg,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,audio/mpeg,video/mp4,application/pdf,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation,text/plain\r\nAccept-Language: en-US,en-GB\r\nConnection: keep-alive\r\n\r\n'
		request = bytes(req_cmd, 'utf-8')
		sent = 0
		print('Sending request to',domain,'server!\n')
		while sent < len(request):
			sent = sent + sock.send(request[sent:])
	except socket.error:
		Handle_Lost_Connection(sock)

def Get_File_Type(res):
	try:
		decoded_res = res.decode("utf-8")
	except:
		decoded_res = res.decode("iso-8859-1")
	file_type = "None"
	lines = decoded_res.split("\r\n")
	for line in lines:
		if line.startswith("Transfer-Encoding: chunked"):
			file_type = "transfer"
			return file_type , None
		if line.startswith("Content-Length:"):
			file_type = "content"
			content_len = int(line.split(": ")[1])
			break	
	return file_type , content_len

def Handle_Url(url):
	# Get Domain and Subdomain
	if url.startswith('http://'):
		url = url.replace('http://','')

	if url.find('/') != -1:
		suburls = url.split('/')
		domain = suburls[0]
		suburls.pop(0)
		subdomain = '/'.join(suburls)

	else:
		domain = url
		subdomain = ''

	return domain , subdomain

def Get_File_Name(domain,subdomain):
	file_name = domain

	if subdomain != '':
		if subdomain[-1] == '/':
			subfolder_name = subdomain.split('/')[-2]
			download_type = 'folder'
			return subfolder_name , download_type
		if subdomain.split('/')[-1] == '':
			file_name += '_index.html'
		elif '.' not in subdomain.split('/')[-1]:
			file_name += '_' + subdomain.split('/')[-1] + '_index.html'
		else:
			file_name += '_' + subdomain.split('/')[-1]
	else:
		file_name += '_index.html'

	download_type = 'single'

	return file_name , download_type

def Receive_Header(sock):
	try:
		head = b''
		while b'\r\n\r\n' not in head:
			read_byte = sock.recv(1)
			head += read_byte
			if not read_byte:
				break
	except socket.error:
		Handle_Lost_Connection(sock)
	return head

def Read_Content_Len_Type(sock,ctlen):

	file_content = b''

	try:
		while len(file_content) < ctlen:
			response = sock.recv(1024)
			file_content += response
			if not response:
				break

	except socket.error:
		Handle_Lost_Connection(sock)

	return file_content

def Read_Transfer_Encoding_Type(sock):
	
	file_content = b''

	try:
		while True:

			len_str = sock.recv(2)
			while len_str[-2:] != b"\r\n":
				len_str += sock.recv(1)
			chunk_len = int(len_str[:-2],16)
			if chunk_len == 0:
				break
			else:
				chunk_read = b''

				while len(chunk_read) < chunk_len:
					chunk_read = sock.recv(chunk_len)
					file_content += chunk_read

				chunk_read = sock.recv(2)
				file_content += chunk_read
	except socket.error:
		Handle_Lost_Connection(sock)
	return file_content

def Get_Content(sock,type,ctlen):
	if type == 'content':
		content = Read_Content_Len_Type(sock,ctlen)
	elif type == 'transfer':
		content = Read_Transfer_Encoding_Type(sock)

	return content

def Download_File(name,content):
	outfile = open(name,'wb')
	outfile.write(content)
	outfile.close()
	print('The file is saved in',name)

def Download_Single(domain,subdomain,filename):
	client = Connect(domain)
	Send_Request(client,domain,subdomain)
	header = Receive_Header(client)
	file_type , content_length = Get_File_Type(header)
	file_content = Get_Content(client,file_type,content_length)
	Download_File(filename,file_content)
	client.close()

def Download_Folder(url,domain,subdomain,foldername):
	client = Connect(domain)
	Send_Request(client,domain,subdomain)

	header = Receive_Header(client)
	file_type , content_length = Get_File_Type(header)
	file_content = Get_Content(client,file_type,content_length)

	#Handle the folder path name
	current_dir , dir_divider = Get_Directory()
	current_dir += dir_divider
	foldername = domain + "_" + foldername 
	folderpath = os.path.join(current_dir,foldername)
	#Create a new directory with the path name above
	if os.path.exists(folderpath):
		shutil.rmtree(folderpath)
	os.mkdir(folderpath)
	client.close()
	subfiles = re.findall(r'href=[\'\"]?([^\'\" >]+)',file_content.decode('utf8'))
	sub_url = {}
	
	for subfilename in subfiles:
		if subfilename.startswith('?') or subfilename.startswith('#'):
			continue
		if subfilename.startswith('/'):
			SubUrl1 = domain + subfilename
			if '/' in subfilename:
				sub = subfilename.split('/')
				if subfilename.endswith('/'):
					subname = sub[-2] + '_index.html'
				else:
					subname = sub[-1] + '_index.html'
			sub_url[SubUrl1] = subname

		else:
			SubUrl2 = url + subfilename
			sub_url[SubUrl2] = subfilename

	#Download the files in the folder using threading
	if len(sub_url) != 0:
		thread_lis = list()

		for sub in sub_url:
			sub_domain , sub_subdomain = Handle_Url(sub)
			sub_filename = folderpath + dir_divider + sub_url[sub]
			thread = threading.Thread(target=Download_Single,args=(domain,sub_subdomain,sub_filename,))
			thread_lis.append(thread)
			thread.start()
			print('Downloading the',sub_url[sub],'file...')
		for thread in thread_lis:
			thread.join()

	else:
		print('There is no file in the folder!!!')

def SingleConnect(url):
	
	Domain , Subdomain = Handle_Url(url)
	File_Name , Download_Type = Get_File_Name(Domain,Subdomain)
	
	if Download_Type == 'single':
		Download_Single(Domain,Subdomain,File_Name)

	elif Download_Type == 'folder':
		Download_Folder(url,Domain,Subdomain,File_Name)

#main

Port = 80

UrlSet = input('Enter one or multiple URLs (separated by spaces) you want to connect: ')

Threads_Lis = list()

for url in UrlSet.split(' '):
	Thread = threading.Thread(target=SingleConnect,args=(url,))
	Threads_Lis.append(Thread)
	Thread.start()

for thread in Threads_Lis:
	thread.join()

print("\nClosing the program!...\n")
