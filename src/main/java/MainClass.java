import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: 노상현
 * Date: 2020-02-11
 * Time: 오후 4:01
 */
public class MainClass {
    public static void main(String[] arg) {
        try {
            File file = null;
            // 큐알이미지를 저장할 디렉토리 지정
            file = new File("D:\\work\\cleanbrain-project\\cleanbrain-qrcode-create-example\\qrcode-image");
            if (!file.exists()) {
                file.mkdirs();
            }
            // QR 코드 데이터
            // String qrCodeData = new String("https://www.nandsoft.co.kr".getBytes("UTF-8"), "ISO-8859-1");
            String qrCodeData = new String("{\"id\": \"test\",\"pw\": \"12345678\"}".getBytes("UTF-8"), "ISO-8859-1");
            // 큐알코드 바코드 생상값
            // int qrcodeColor =   0xFF2e4e96;
            int qrcodeColor = -16777216;
            // 큐알코드 배경색상값
            int backgroundColor = 0xFFFFFFFF;

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            // 3,4번째 parameter값 : width/height값 지정
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 200, 200);
            //
            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor, backgroundColor);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
            // ImageIO를 사용한 바코드 파일쓰기
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String timeFormat = sdf.format(new Date(System.currentTimeMillis()));
            ImageIO.write(bufferedImage, "png", new File("D:\\work\\cleanbrain-project\\cleanbrain-qrcode-create-example\\qrcode-image\\qrcode_" + timeFormat + ".png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
