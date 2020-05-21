package poly.util;

import java.io.File;

public class FileUtil {

	/**
	 * ���� ��¥�� �������� �� /��/�� ���� �����ϱ�
	 * 
	 * @param ������ ����Ǵ� ROOT����
	 * @return ������ ����Ǳ� ���� ������ ��ü ���� ���
	 */
	public static String mkdirForDate(String uploadDir) {

		String path = uploadDir + DateUtil.getDateTime("/yyyy/MM/dd"); // ���� ���
		
		File Folder = new File(path);

		if (!Folder.exists()) {
			Folder.mkdirs(); // ���� �����մϴ�.

		}
		
		return path;
	}

}