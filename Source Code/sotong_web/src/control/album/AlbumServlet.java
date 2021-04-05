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
		else if(uri.contains("/album_image_info")) //�ٹ� ���� �о����.
		{
			requestImageInfo(request,response);
		}
			//����
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
				System.out.println("App ����");
				String str = "200|";
				String data = "";
				for (int i = 0; i < imageList.length; i++) 
				{
						data = 	imageList[i].getImageCode()+"|"//�̹������			
								+imageList[i].getImageName()+"|"//�̹����ڵ�	
								+imageList[i].getImageWrittenDate()+"|"//�̹��� �ۼ���
								+imageList[i].getGalleryCategoryCode()+"|"//�̹��� �з� �ڵ�
								.trim();
								out.println(str + data + "".trim());
							System.out.println("albumInfo:" + str + data + "".trim());
				}
				
				

			} else if (devide.equals("2000")) {
				System.out.println("Web ����");
				// �� �ڵ� ������.
			}
		} else {
			if (devide.equals("1000")) {
				System.out.println("App ����");

			} else if (devide.equals("2000")) {
				System.out.println("Web ����");
				// �� �ڵ� ������.
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
				System.out.println("App ����");
				String str = "200|";
				String data = "";
						data = 	imageInfo.getImageCode()+"|"//�̹������			
								+imageInfo.getImageCode()+"|"//�̹����ڵ�	
								+imageInfo.getImageWrittenDate()+"|"//�̹��� �ۼ���
								+imageInfo.getGalleryCategoryCode()+"|"//�̹��� �з� �ڵ�
								.trim();
								out.println(str + data + "".trim());
			
			} 
			else if (devide.equals("2000")) 
			{
				System.out.println("Web ����");
				// �� �ڵ� ������.
			}
		} else {
			if (devide.equals("1000")) {
				System.out.println("App ����");

			} else if (devide.equals("2000")) {
				System.out.println("Web ����");
				// �� �ڵ� ������.
			}
		}
	}

}
