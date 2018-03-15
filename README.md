# fbAuto


To run the application it is needed the Selenium for java, Commons io libraries and the Geckodriver binary. <br>
Running the application from the dev ide you just need to clone the repository or download any release. 
To run it with a .jar file it is needed to have the Geckodriver on the same folder. All other files should be on a folder, on the root named "app", you have to create the folder by hand. <br> 
The other required file to run this is the config.xml file on the app folder. <br><br><br> 
Example for the file
config.xml. <br>
The file should be located on the project app folder. <br>
The values for the fields groups, pages and pagePosts have to be 0 or 1. If the value is 1 the application will run that part, if 0 it will not.

\<?xml version="1.0" encoding="UTF-8"?> <br>
\<config> </br>
&nbsp;&nbsp;&nbsp;&nbsp;\<url>https://facebook.com/ \</url> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<login>name@mail.com \</login> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<pwd>password\</pwd> <br> 
&nbsp;&nbsp;&nbsp;&nbsp;\<groups>0\</groups> <br> 
&nbsp;&nbsp;&nbsp;&nbsp;\<pages>0\</pages> <br> 
&nbsp;&nbsp;&nbsp;&nbsp;\<pagePosts>1\</pagePosts> <br> 
\</config>
<br>
<br>
<br>
<br>
Example for the file search.xml. <br>
The file should be located on the app folder. <br>
The data for this file have to be copied from the file app/data/PagesList.xml, created by the application. <br><br>
\<?xml version="1.0" encoding="UTF-8"?> <br>
\<search> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<pageId>1\</pageId> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<pageName>Test\</pageName> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<pageUrl>https://www.facebook.com/Test/ \</pageUrl> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<postsNum>5\</postsNum> <br>
\</search>
<br>
<br>
<br>
<br>
Example for the file XML files on the toPost folder. <br>
Create a folder named toPost, on the app folder. <br>
On that folder add XML files with the name of the page where you want to share the post. Example PageName1_.xml, PageName2_.xml <br>
You can only add one post and multiple groups, to each file. The total value should be the number of groups you have inserted below. <br>
The values to fill the file content should be gotten from the files on the data folder. <br>
The done value is to verify if the post has to be publish or not. The value 0 means that it will be posted the next time the application run. <br>
If you need to add multiple lines on the text, use the /n for the application to add a new line for the next text. Example Line 1./nLine2
<br>
<br>
\<?xml version="1.0" encoding="UTF-8"?> <br>
\<toPost> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<done>0\</done> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<postText>Text to the post\</postText> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<postUrl>http://facebook.com/post \</postUrl> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<groups> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<total>2\</total> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<group> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<name>Group Name\</name> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<url>http://facebook.com/group \</url> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\</group> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<group> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<name>Group Name2\</name> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<url>http://facebook.com/group2 \</url> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\</group> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\</groups> <br>
\</toPost>
<br>
<br>
<br>
<br>
# Docker


Added the structure to be able to run the application on a docker. <br>
You to have the Docker application installed on your PC, https://www.docker.com/community-edition#/download <br>
To use it you just have to get the docker.zip from the release, uncompress the folder on it. <br>
<br>
Create a config.xml file like the text above, and drop it on the extracted folder. <br>
On the same folder create a another file named config. On it paste the full path where you want the application to create the logs and all the other files, and configurations. <br>
Don't need to download the Geckodriver and the fbAuto.jar, these files will be downloaded when the docker is created. <br>
<br>
After creating the two configuration files on the docker folder run the rebuild.sh, it will create the docker image. To create the container execute the start.sh file after the rebuild. <br>
Both these files are located on the folder scripts. <br>
<br>
Now you just have to enter the container and run the script run.sh.