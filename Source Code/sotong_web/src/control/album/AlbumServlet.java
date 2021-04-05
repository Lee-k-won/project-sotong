package control.album;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.album.AlbumManager;
import dao.sotong.ImageVO;

/**
 * Servlet implementation class AlbumServlet
 */
@WebServlet({ "/AlbumServlet","/album_image_info","/get_image_list.do" })
public class AlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlbumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		//System.out.println("uri:"+uri);
		if(uri.contains("get_image_list.do"))
		{
			requestAlbumList(request,response);
		}
		else if(uri.contains("/album_image_info")) //앨범 정보 읽어오기.
		{
			requestImageInfo(request,response);
		}
			//수정
		/*int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		switch (action) {
		case "album_list" :
			requestAlbumList(request,response);
			break;
		case "album_image_info" :
			requestImageInfo(request,response);
			break;
		}*/
	}
	public void requestAlbumList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		AlbumManager manager=new AlbumManager();
		String homeCode=request.getParameter("homeCode");
		String devide=request.getParameter("serviceRoute");
		ImageVO[] imageList=manager.getAlbumListByHomeCode(homeCode);
		if (imageList!= null) {
			if (devide.equals("1000")) {
				System.out.println("App 접속");
				String str = "200|";
				String data = "";
				for (int i = 0; i < imageList.length; i++) 
				{
						data = 	imageList[i].getImageCode()+"|"//이미지경로			
								+imageList[i].getImageName()+"|"//이미지코드	
								+imageList[i].getImageWrittenDate()+"|"//이미지 작성일
								+imageList[i].getGalleryCategoryCode()+"|"//이미지 분류 코드
								.trim();
								out.println(str + data + "".trim());
							System.out.println("albumInfo:" + str + data + "".trim());
				}
				
				

			} else if (devide.equals("2000")) {
				System.out.println("Web 접속");
				// 웹 코드 들어가야함.
			}
		} else {
			if (devide.equals("1000")) {
				System.out.println("App 접속");

			} else if (devide.equals("2000")) {
				System.out.println("Web 접속");
				// 웹 코드 들어가야함.
			}
		}
	}

		
	
	public void requestImageInfo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		AlbumManager manager=new AlbumManager();
		String imageCode=request.getParameter("imageCode");
		String devide=request.getParameter("serviceRoute");
		ImageVO imageInfo=manager.getImageInfo(imageCode);
		if (imageInfo!= null) {
			if (devide.equals("1000")) 
			{
				System.out.println("App 접속");
				String str = "200|";
				String data = "";
						data = 	imageInfo.getImageCode()+"|"//이미지경로			
								+imageInfo.getImageCode()+"|"//이미지코드	
								+imageInfo.getImageWrittenDate()+"|"//이미지 작성일
								+imageInfo.getGalleryCategoryCode()+"|"//이미지 분류 코드
								.trim();
								out.println(str + data + "".trim());
			
			} 
			else if (devide.equals("2000")) 
			{
				System.out.println("Web 접속");
				// 웹 코드 들어가야함.
			}
		} else {
			if (devide.equals("1000")) {
				System.out.println("App 접속");

			} else if (devide.equals("2000")) {
				System.out.println("Web 접속");
				// 웹 코드 들어가야함.
			}
		}
	}

}
