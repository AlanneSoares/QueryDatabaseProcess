package mprj.mp.br;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;


public class Application {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException, SQLException {
        org.jsoup.nodes.Document jsoup;
        org.jsoup.nodes.Element form;
        org.jsoup.nodes.Element href;
        DocumentBuilderFactory dbf;
        DocumentBuilder db;
        org.w3c.dom.Document d;
        Node descrClasse;
        Node orgaoJulgador;
        String cnj;
        String query;
        String driver;
        String url;
        String username;
        String password;
        String numeroTJ;
        String value;
        int cnj22 = 22;
        int cnj23 = 23;
        int cnj24 = 24;
        query = "";
        url = "";
        username = "";
        password = "";


        Class.forName(driver = "oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            // com traços e pontos
            cnj = rs.getString("cnj"); //.replaceAll("[^0-9]", "");  //sem traços e pontos
            int array[] = new int[0];

            for (int i = 0; i <= array.length; i++) {
                if (cnj.length() == cnj22) { // 22 dígitos acrescenta 3 dígitos e imprime
                    // CHAMA A URL
                    jsoup = Jsoup.connect(Consulta.info("000" + cnj)).get(); // abre o link
                    // SE ENCONTRAR O ID NUMPROC
                    if (jsoup.toString().contains("NumProc")) { // se houver 1ª página encontre o Numproc
                        numeroTJ = jsoup.getElementById("NumProc").attr("value"); // pega valor do atributo
                        // GERA O ARQUIVO
                        RecuperaUrlPost.sendPost(numeroTJ);
                        // LÊ O ARQUIVO
                        dbf = DocumentBuilderFactory.newInstance();
                        db = dbf.newDocumentBuilder();
                        d = db.parse(new File("dados.xml"));
                        // LÊ OS ELEMENTOS DESEJADOS
                        descrClasse = d.getElementsByTagName("DescrClasse").item(0);
                        orgaoJulgador = d.getElementsByTagName("OrgaoJulgador").item(0);
                        // IMPRIME NO CONSOLE
                        System.out.println(
                                "---------------------------------------------------------------------" +
                                        "\n                             Resultado                               " +
                                        "\n                CNJ: " + "000" + cnj +
                                        "\n---------------------------------------------------------------------" +
                                        "\nNúmero TJ: " + numeroTJ +
                                        "\nClasse: " + descrClasse.getTextContent() +
                                       "\nÓrgão Julgador: " + orgaoJulgador.getTextContent()
                        );
                    } else { // se houver 2ª página, encontre o formulário,
                        form = jsoup.getElementById("form");
                        href = form.getElementsByAttribute("href").get(1);
                        
                        if (href.toString().contains("CAMARA")) {
                            value = href.attr("href");
                            //CONECTA A URL ENCONTRADA - http://www4.tjrj.jus.br/ejud/ConsultaProcesso.aspx?N= E ENCONTRA O VALOR DO CNJ
                            jsoup = Jsoup.connect(value).get();
                            numeroTJ = jsoup.getElementById("NumProc").attr("value");
                            // GERA O ARQUIVO
                            RecuperaUrlPost.sendPost(numeroTJ);
                            // LÊ O ARQUIVO
                            dbf = DocumentBuilderFactory.newInstance();
                            db = dbf.newDocumentBuilder();
                            d = db.parse(new File("dados.xml"));
                            // LÊ OS ELEMENTOS DESEJADOS
                            descrClasse = d.getElementsByTagName("DescrClasse").item(0);
                            orgaoJulgador = d.getElementsByTagName("OrgaoJulgador").item(0);
                            // IMPRIME NO CONSOLE
                            System.out.println(
                                    "-----------------------------------------------------------------------" +
                                            "\n                              Resultado                              " +
                                            "\n                CNJ: " + "000" + cnj +
                                            "\n---------------------------------------------------------------------" +
                                            "\nNúmero TJ: " + numeroTJ +
                                            "\nClasse: " + descrClasse.getTextContent() +
                                            "\nÓrgão Julgador: " + orgaoJulgador.getTextContent()
                            );
                        } else if (cnj.length() == cnj23) { // 23 dígitos acrescenta 3 dígitos e imprime
                            // CHAMA A URL
                            jsoup = Jsoup.connect(Consulta.info("00" + cnj)).get(); // abre o link
                            if (jsoup.toString().contains("NumProc")) {
                                numeroTJ = jsoup.getElementById("NumProc").attr("value");
                                // GERA O ARQUIVO
                                RecuperaUrlPost.sendPost(numeroTJ);
                                // LÊ O ARQUIVO
                                dbf = DocumentBuilderFactory.newInstance();
                                db = dbf.newDocumentBuilder();
                                d = db.parse(new File("dados.xml"));
                                // LÊ OS ELEMENTOS DESEJADOS
                                descrClasse = d.getElementsByTagName("DescrClasse").item(0);
                                orgaoJulgador = d.getElementsByTagName("OrgaoJulgador").item(0);
                                // IMPRIME NO CONSOLE
                                System.out.println(
                                        "---------------------------------------------------------------------" +
                                                "\n                             Resultado                               " +
                                                "\n                CNJ: " + "00" + cnj +
                                                "\n---------------------------------------------------------------------" +
                                                "\nNúmero TJ: " + numeroTJ +
                                                "\nClasse: " + descrClasse.getTextContent() +
                                                "\nÓrgão Julgador: " + orgaoJulgador.getTextContent()

                                );

                            } else {
                                form = jsoup.getElementById("form");
                                href = form.getElementsByAttribute("href").get(1);
                                if (href.toString().contains("CAMARA")) {
                                    value = href.attr("href");
                                    //CONECTA A URL ENCONTRADA - http://www4.tjrj.jus.br/ejud/ConsultaProcesso.aspx?N= E ENCONTRA O VALOR DO CNJ
                                    jsoup = Jsoup.connect(value).get();
                                    numeroTJ = jsoup.getElementById("NumProc").attr("value");
                                    // GERA O ARQUIVO
                                    RecuperaUrlPost.sendPost(numeroTJ);
                                    // LÊ O ARQUIVO
                                    dbf = DocumentBuilderFactory.newInstance();
                                    db = dbf.newDocumentBuilder();
                                    d = db.parse(new File("dados.xml"));
                                    // LÊ OS ELEMENTOS DESEJADOS
                                    descrClasse = d.getElementsByTagName("DescrClasse").item(0);
                                    orgaoJulgador = d.getElementsByTagName("OrgaoJulgador").item(0);
                                    // IMPRIME NO CONSOLE
                                    System.out.println(
                                            "-----------------------------------------------------------------------" +
                                                    "\n                              Resultado                              " +
                                                    "\n                CNJ: " + "00" + cnj +
                                                    "\n---------------------------------------------------------------------" +
                                                    "\nNúmero TJ: " + numeroTJ +
                                                    "\nClasse: " + descrClasse.getTextContent() +
                                                    "\nÓrgão Julgador: " + orgaoJulgador.getTextContent()
                                    );
                                } else if (cnj.length() == cnj24) { // 24 dígitos acrescenta 3 dígitos e imprime
                                    // CHAMA A URL
                                    jsoup = Jsoup.connect(Consulta.info("0" + cnj)).get();
                                    if (jsoup.toString().contains("NumProc")) {
                                        numeroTJ = jsoup.getElementById("NumProc").attr("value");
                                        // GERA O ARQUIVO
                                        RecuperaUrlPost.sendPost(numeroTJ);
                                        // LÊ O ARQUIVO
                                        dbf = DocumentBuilderFactory.newInstance();
                                        db = dbf.newDocumentBuilder();
                                        d = db.parse(new File("dados.xml"));
                                        // LÊ OS ELEMENTOS DESEJADOS
                                        descrClasse = d.getElementsByTagName("DescrClasse").item(0);
                                        orgaoJulgador = d.getElementsByTagName("OrgaoJulgador").item(0);
                                        // IMPRIME NO CONSOLE
                                        System.out.println(
                                        "---------------------------------------------------------------------" +
                                        "\n                             Resultado                               " +
                                        "\n                CNJ: " + "0" + cnj +
                                        "\n---------------------------------------------------------------------" +
                                        "\nNúmero TJ: " + numeroTJ +
                                        "\nClasse: " + descrClasse.getTextContent() +
                                        "\nÓrgão Julgador: " + orgaoJulgador.getTextContent()
                                        );
                                    } else {
                                        // SE ENCONTRAR O ID NUMPROC
                                        form = jsoup.getElementById("form");
                                        href = form.getElementsByAttribute("href").get(1);
                                        if (href.toString().contains("CAMARA")) {
                                            value = href.attr("href");
                                            //CONECTA A URL ENCONTRADA - http://www4.tjrj.jus.br/ejud/ConsultaProcesso.aspx?N= E ENCONTRA O VALOR DO CNJ
                                            jsoup = Jsoup.connect(value).get();
                                            numeroTJ = jsoup.getElementById("NumProc").attr("value");
                                            // GERA O ARQUIVO
                                            RecuperaUrlPost.sendPost(numeroTJ);
                                            // LÊ O ARQUIVO
                                            dbf = DocumentBuilderFactory.newInstance();
                                            db = dbf.newDocumentBuilder();
                                            d = db.parse(new File("dados.xml"));
                                            // LÊ OS ELEMENTOS DESEJADOS
                                            descrClasse = d.getElementsByTagName("DescrClasse").item(0);
                                            orgaoJulgador = d.getElementsByTagName("OrgaoJulgador").item(0);
                                            // IMPRIME NO CONSOLE
                                            System.out.println(
                                                    "-----------------------------------------------------------------------" +
                                                            "\n                              Resultado                              " +
                                                            "\n                CNJ: " + cnj +
                                                           "\n---------------------------------------------------------------------" +
                                                            "\nNúmero TJ: " + numeroTJ +
                                                            "\nClasse: " + descrClasse.getTextContent() +
                                                            "\nÓrgão Julgador: " + orgaoJulgador.getTextContent()
                                            );
                                        } else {
                                            System.out.println("Não encontrado!");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
