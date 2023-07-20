# SecurityFilter
Security Filter for Tomcat 9 to add Certificate Information to RequestHeaders

This is working from the code found here:  https://wilddiary.com/adding-custom-headers-java-httpservletrequest/

For me this didn't work in Tomcat 9.  Had to find the getHeaders piece, once that was added everything worked.
