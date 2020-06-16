package com.example.stirdiary;

import android.content.Context;
import com.google.gson.Gson;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

class DiaryList {
    private List<Diary> l;
    public DiaryList (List<Diary> _l) {
        l = _l;
    }
    public List<Diary> getList () {
        return l;
    }
}

public class DiaryFileHelper {

    private Context mContext;

    private static Gson gson = new Gson();

    public DiaryFileHelper(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public void saveDiaryToFile(String fileName, Diary diary) throws Exception {
        FileHelper fh = new FileHelper(mContext);
        String diaryJsonText = gson.toJson(diary);
        fh.save(fileName, diaryJsonText);
    }

    public Diary readDiaryFromFile(String fileName) throws IOException {
        FileHelper fh = new FileHelper(mContext);
        String diaryJsonText = fh.read(fileName);
        Diary diary = gson.fromJson(diaryJsonText, Diary.class);
        return diary;
    }

    public void saveDiaryListToFile(String fileName, List<Diary> diary) throws Exception {
        FileHelper fh = new FileHelper(mContext);
        String diaryListJsonText = gson.toJson(new DiaryList(diary));
        fh.save(fileName, diaryListJsonText);
    }

    public List<Diary> readDiaryListFromFile(String fileName) throws IOException {
        FileHelper fh = new FileHelper(mContext);
        String diaryListJsonText = fh.read(fileName);
        DiaryList diaryList = gson.fromJson(diaryListJsonText, DiaryList.class);
        return diaryList.getList();
    }

    public void generateDiarySVG(String fileName, Diary diary) throws Exception {
        String bottleFileName = "/assets/glasses/" + "glass" + diary.getBottle_kind() + ".svg";
        InputStream is = getClass().getResourceAsStream(bottleFileName);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document document = db.parse(is);
        String result = null;

        if (document != null) {
            StringWriter strWtr = new StringWriter();
            StreamResult strResult = new StreamResult(strWtr);
            TransformerFactory tfac = TransformerFactory.newInstance();
            try {
                javax.xml.transform.Transformer t = tfac.newTransformer();
                t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                t.setOutputProperty(OutputKeys.INDENT, "yes");
                t.setOutputProperty(OutputKeys.METHOD, "xml"); // xml, html,
                // text
                t.setOutputProperty(
                        "{http://xml.apache.org/xslt}indent-amount", "4");
                t.transform(new DOMSource(document.getDocumentElement()),
                        strResult);
            } catch (Exception e) {
                System.err.println("XML.toString(Document): " + e);
            }
            result = strResult.getWriter().toString();
            try {
                strWtr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileHelper fh = new FileHelper(mContext);
        fh.save(fileName, result);
    }
}
