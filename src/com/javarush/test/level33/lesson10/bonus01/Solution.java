package com.javarush.test.level33.lesson10.bonus01;
import org.w3c.dom.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
/* Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тэгом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:  toXmlWithComment(firstSecondObject, "second", "it's a comment")
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second><![CDATA[need CDATA because of < and >]]></second>
    <!--it's a comment-->
    <second/>
</first>
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        StringWriter stringWriter = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            document.setStrictErrorChecking(false);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(obj, document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 4);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            NodeList nodeList = document.getElementsByTagName(tagName);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                node.getParentNode().insertBefore(document.createComment(comment), node);
            }
            NodeList childNodes = document.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);
                nodes(node, document);
            }
            stringWriter = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
        }
        catch (Exception e) {
        }
        return stringWriter.toString();
    }
    private static void nodes(Node node, Document document) {
        if (node.hasChildNodes()) {
            NodeList list = node.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                nodes(list.item(i), document);
            }
        } else {
            if (node.getNodeType() == Node.TEXT_NODE) {
                String nodeText = node.getTextContent();
                if (nodeText.contains("&") || nodeText.contains("\"") || nodeText.contains("<") || nodeText.contains(">") || nodeText.contains("‘")) {
                    node.getParentNode().replaceChild(document.createCDATASection(nodeText), node);
                }
            }
        }
    }
    public static void main(String[] args) {
        String result = Solution.toXmlWithComment(new AnExample(), "needCDATA", "it's a comment - <needCDATA>");
        System.out.println(result);
    }
    @XmlType(name = "anExample")
    @XmlRootElement
    public static class AnExample {
        public String otherLine = "otherLine";
        @XmlElementWrapper(name = "need")
        public String[] needCDATA = new String[]{"need CDATA because of < and >", "", "hello world"};
    }
}
