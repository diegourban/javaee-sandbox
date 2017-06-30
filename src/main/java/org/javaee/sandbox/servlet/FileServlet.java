package org.javaee.sandbox.servlet;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javaee.sandbox.infra.FileSaver;

@WebServlet("/file/*")
public class FileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6739464747686326541L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String relativePath = req.getRequestURI().split("/file")[1];

		final Path filePath = Paths.get(FileSaver.SERVER_PATH + "/" + relativePath);
		final FileNameMap fileNameMap = URLConnection.getFileNameMap();
		final String contentType = fileNameMap.getContentTypeFor("file:" + filePath);

		res.reset(); // removendo qualquer atribuição pelo JSF
		res.setContentType(contentType);
		res.setHeader("Content-Length", String.valueOf(Files.size(filePath)));
		res.setHeader("Content-Disposition", "filename=\"" + filePath.getFileName().toString() + "\"");
		
		FileSaver.transfer(filePath, res.getOutputStream());
	}

}
