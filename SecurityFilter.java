package webi2ms.addHeader;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import webi2ms.addHeader.MutableHttpServletRequest;

//import java.util.Enumeration;

public class SecurityFilter implements javax.servlet.Filter
{
 @Override public void destroy() 
 {
 }

 @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
  {
   //System.out.println("webi2ms.addHeader.doFilter-Start.");
   HttpServletRequest req = (HttpServletRequest) request;
   MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);

   X509Certificate[] certs = (X509Certificate[]) req.getAttribute("javax.servlet.request.X509Certificate");

   if(certs != null)
     {
      //System.out.println("webi2ms.addHeader.doFilter-SSL_CLIENT_S_DN="+certs[0].getSubjectX500Principal().getName());
      //System.out.println("webi2ms.addHeader.doFilter-SSL_CERTIFICATES_FOUND="+Integer.toString(certs.length));
      //System.out.println("webi2ms.addHeader.doFilter-tims-test-header=tims-test-value");
      mutableRequest.putHeader("SSL_CLIENT_S_DN", certs[0].getSubjectX500Principal().getName());
      mutableRequest.putHeader("SSL_CLIENT_I_DN", certs[0].getIssuerX500Principal().getName());
      //mutableRequest.putHeader("SSL_CERTIFICATES_FOUND", Integer.toString(certs.length));
      chain.doFilter(mutableRequest, response);
     }

   //Enumeration<String> nheaderNames = mutableRequest.getHeaderNames();
   // 
   //     while (nheaderNames.hasMoreElements()) {
   //
   //         String nheaderName = nheaderNames.nextElement();
   //         System.out.print(nheaderName);
   //         System.out.print("=");
   //
   //         Enumeration<String> nheaders = mutableRequest.getHeaders(nheaderName);
   //         while (nheaders.hasMoreElements()) {
   //             String nheaderValue = nheaders.nextElement();
   //             System.out.print(nheaderValue+"\n");
   //        }
   // 
   //     }
   //System.out.println("webi2ms.addHeader.doFilter-Done.");
  }

 @Override public void init(FilterConfig filterConfig) throws ServletException
  {
  }
}