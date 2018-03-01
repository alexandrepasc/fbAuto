# fbAuto

Example for the file
config.xml. <br>
The file should be located on the project root folder.

\<?xml version="1.0" encoding="UTF-8"?> <br>
\<config> </br>
&nbsp;&nbsp;&nbsp;&nbsp;\<url>https://facebook.com/ \</url> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<login>name@mail.com \</login> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<pwd>password \</pwd> <br> 
\</config>
<br>
<br>
<br>
<br>
Example for the file search.xml. <br>
The file should be located on the project root folder. <br>
The data for this file have to be copied from the file data/PagesList.xml, created by the app. <br><br>
\<?xml version="1.0" encoding="UTF-8"?> <br>
\<search> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<pageId>1 \</pageId> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<pageName>Test \</pageName> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<pageUrl>https://www.facebook.com/Test/ \</pageUrl> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<postsNum>5 \</postsNum> <br>
\</search>
<br>
<br>
<br>
<br>
Example for the file XML files on the toPost folder. <br>
Create a folder named toPost, on the project root. <br>
On that folder add XML files with the name of the page where you want to share the post. Example PageName1_.xml, PageName2_.xml <br>
You can only add one post and multiple groups, to each file. The total value should be the number of groups you have inserted below. <br>
The values to fill the file content should be gotten from the files on the data folder.
The done value is to verify if the post has to be publish or not. The value 0 means that it will be posted the next time the application run.
<br>
<br>
\<?xml version="1.0" encoding="UTF-8"?> <br>
\<toPost> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<done>0\</done> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<postText>Text to the post\</postText> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<postUrl>http://facebook.com/post\</postUrl> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<groups> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<total>2\</total> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<group> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<name>Group Name\</name> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<url>http://facebook.com/group\</url> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\</group> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<group> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<name>Group Name2\</name> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<url>http://facebook.com/group2\</url> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\</group> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\</groups> <br>
\</toPost>
