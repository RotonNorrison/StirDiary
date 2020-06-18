package com.example.stirdiary;

import android.content.Context;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiaryThumbnailHelper {

    private final Context mContext;

    public DiaryThumbnailHelper(Context mContext) {
        super();
        this.mContext = mContext;
    }

    private Document readSVGFromAssets(String assetName) throws IOException, ParserConfigurationException, SAXException {
//        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
//        String parser = XMLResourceDescriptor.getXMLParserClassName();
//        SAXDocumentFactory f = new SAXDocumentFactory(impl, parser);
////        String uri = "file:///android_assets/glass1.svg";
//        Document doc = f.createDocument(uri, is);
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();

        InputStream input = mContext.getAssets().open(assetName);

        return builder.parse(input);
    }

    private String generateSVG(Document doc) throws TransformerException, UnsupportedEncodingException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        transformer.transform(new DOMSource(doc), new StreamResult(outputStream));
        return outputStream.toString("UTF-8");
    }

//    private void convertToPng(String svgCode, OutputStream outputStream)
//            throws TranscoderException, IOException {
//        try {
//            byte[] bytes = svgCode.getBytes("utf-8");
//            JPEGTranscoder t = new JPEGTranscoder();
//            TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
//            TranscoderOutput output = new TranscoderOutput(outputStream);
//            t.transcode(input, output);
//            outputStream.flush();
//        } finally {
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

//    private void convertToPng(String svgCode, String pngFilePath) throws IOException,
//            TranscoderException {
//
//        File file = new File(pngFilePath);
//
//        FileOutputStream outputStream = null;
//        try {
//            file.createNewFile();
//            outputStream = new FileOutputStream(file);
//            convertToPng(svgCode, outputStream);
//        } finally {
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    public void generateDiarySVG(String fileName, Diary diary) throws Exception {
        int bottle_kind=diary.getBottle_kind();
        Document doc = readSVGFromAssets("glass" + bottle_kind + "_base.svg");
//        String docStr = generateSVG(doc);
//        FileHelper fh=new FileHelper(mContext);
//        fh.save(fileName, docStr);
        ArrayList<AddWine> winelist = diary.getWinelist();
        double totalVolume = 0;
        for (AddWine wine : winelist) {
            totalVolume += wine.getVolume();
        }

        Node canvas = doc.getElementsByTagName("g").item(0);
        Node defs = doc.getElementsByTagName("defs").item(0);
//        System.out.println(canvas.getNodeName());

        Map<String, String> wineColor = new HashMap<>();
        wineColor.put("vodka", "#f2f2f2");
        wineColor.put("rum", "#f18258");
        wineColor.put("gin", "#f2f2f2");
        wineColor.put("brandy", "#82272d");
        wineColor.put("tequila", "#fffb85");
        wineColor.put("whisky", "#f17324");
        wineColor.put("grenadine", "#ED515C");
        wineColor.put("orange", "#FBB571");
        wineColor.put("cherry", "#FF92FF");
        wineColor.put("lime", "#6AFFAB");
        wineColor.put("triplesec", "#00FFFF");
        wineColor.put("blackberry", "#C11EF1");
        double[] XL = new double[]{66,139.9,112.7,85.3};
        double[] YE = new double[]{135,65.5,96,188.8};
        double[] YL = new double[]{110,51.5,76,153.8};

        double xstart=0, xlength=XL[bottle_kind-1], yend=YE[bottle_kind-1], ylength=YL[bottle_kind-1];

        if(diary.getStirWay()==1) {
            double currentVolume = 0;
            for (AddWine addWine : winelist) {
                Element rect = doc.createElement("rect");
                rect.setAttribute("mask", "url(#water)");
                rect.setAttribute("x", Double.toString(xstart));
                rect.setAttribute("width", Double.toString(xlength));
                rect.setAttribute("fill", wineColor.get(addWine.getWinename()));
                rect.setAttribute("y", Double.toString(yend - (addWine.getVolume() + currentVolume) / totalVolume * ylength));
                rect.setAttribute("height", Double.toString(addWine.getVolume() / totalVolume * ylength));
                canvas.insertBefore(rect, canvas.getFirstChild());
                currentVolume += addWine.getVolume();
            }
        } else if (diary.getStirWay()==2) {
            double currentVolume = 0;
            Element rect = doc.createElement("rect");
            rect.setAttribute("mask", "url(#water)");
            rect.setAttribute("x", Double.toString(xstart));
            rect.setAttribute("width", Double.toString(xlength));
            rect.setAttribute("fill", "url(#winecolor)");
            rect.setAttribute("y", Double.toString(yend-ylength));
            rect.setAttribute("height", Double.toString(ylength));
            canvas.insertBefore(rect, canvas.getFirstChild());

            Element linearGradient = doc.createElement("linearGradient");
            linearGradient.setAttribute("id", "winecolor");
            linearGradient.setAttribute("x1", "0%");
            linearGradient.setAttribute("x2", "0%");
            linearGradient.setAttribute("y1", "0%");
            linearGradient.setAttribute("y2", "100%");
            defs.insertBefore(linearGradient, defs.getFirstChild());

            for (AddWine addWine : winelist) {
                Element stop = doc.createElement("stop");
                stop.setAttribute("stop-color", wineColor.get(addWine.getWinename()));
                stop.setAttribute("stop-opacity", "1");
                stop.setAttribute("offset", Double.toString(100-(currentVolume + addWine.getVolume()/2) / totalVolume * 100)+"%");
                linearGradient.insertBefore(stop, linearGradient.getFirstChild());
                currentVolume += addWine.getVolume();
            }
        } else if (diary.getStirWay()==3) {
            double r=0,g=0,b=0;
            for (AddWine addWine : winelist) {
                String colorCode=wineColor.get(addWine.getWinename());
                r+=(Long.parseLong(colorCode.substring(1,3),16))*addWine.getVolume();
                g+=(Long.parseLong(colorCode.substring(3,5),16))*addWine.getVolume();
                b+=(Long.parseLong(colorCode.substring(5,7),16))*addWine.getVolume();
            }
            System.out.println(r);
            System.out.println(g);
            System.out.println(b);
            Element rect = doc.createElement("rect");
            rect.setAttribute("mask", "url(#water)");
            rect.setAttribute("x", Double.toString(xstart));
            rect.setAttribute("width", Double.toString(xlength));
            rect.setAttribute("fill", String.format("rgb(%d,%d,%d)",
                    Math.round(r/totalVolume),
                    Math.round(g/totalVolume),
                    Math.round(b/totalVolume)));
            rect.setAttribute("y",
                    Double.toString(yend-ylength));
            rect.setAttribute("height", Double.toString(ylength));
            canvas.insertBefore(rect, canvas.getFirstChild());
        }

        String docStr = generateSVG(doc);
//        System.out.println(docStr);
//        convertToPng(docStr, fileName);
        FileHelper fh=new FileHelper(mContext);
        fh.save(fileName, docStr);
    }
}
