package control.home;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.xml.internal.fastinfoset.util.StringArray;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet({ "/ImageServlet", "/image_save.do", "/image_load.do",
		"/image_delete.do" })
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();

		if (uri.contains("/image_save.do")) { //파일 저장시 URI 매핑

			saveImage(request, response);
		}
	}

	public int saveImage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		int res = 0;
		String imgRoute = getServletContext().getInitParameter("imgRoute");
		
		String imageString = request.getParameter("image");//이미지 바이트배열을 응축한 String객체
		String imageName = request.getParameter("imageName");//경로에 저장할 파일명.
		byte[] imageToByte = Base64.decode(imageString);
		
		ByteArrayInputStream bis = new ByteArrayInputStream(imageToByte);
		Iterator<?> readers = ImageIO.getImageReadersByFormatName("PNG");
		ImageReader reader = (ImageReader) readers.next();
		Object source = bis;
		ImageInputStream iis = ImageIO.createImageInputStream(source);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Image image = reader.read(0, param);
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
		image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, null, null);
		File imageFile = new File(imgRoute+imageName); //불러온 파일을 저장할 경로와 파일명.
		
		
		
		boolean isSaved = ImageIO.write(bufferedImage, "jpg", imageFile);//파일이 작성되었는지 여부 체크.
		RequestDispatcher view = null;
		if (isSaved) {//성공
			System.out.println("App 접속");
			out.println("200|");
			// System.out.println("wishInfo" + str + data + "".trim());
			res = 1;
		} else {//실패
			System.out.println("App 접속");
			out.println("500|");
		}
		return res;
	}

}
