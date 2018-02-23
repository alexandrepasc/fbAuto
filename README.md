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
Example for the file XML files on the toPost folder.
<br>
<br>
\<?xml version="1.0" encoding="UTF-8"?> <br>
\<toPost> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<done>0 \</done>
&nbsp;&nbsp;&nbsp;&nbsp;\<groups> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<group> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<name>Group Name \</name> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<url>http://facebook.com/group \</url> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\</group> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\</groups> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\<posts> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<post> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<url>http://facebook.com/post \</url> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\<text>Text to the post \</text>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\</post> <br>
&nbsp;&nbsp;&nbsp;&nbsp;\</posts> <br>
\</toPost>
