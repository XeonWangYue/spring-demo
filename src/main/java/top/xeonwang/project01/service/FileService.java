package top.xeonwang.project01.service;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FopFactoryBuilder;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.convert.out.fo.renderers.FORendererApacheFOP;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.PhysicalFont;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Chen Q.
 */
public class FileService {
    public static void main(String[] args) {

        try {

            WordprocessingMLPackage mlPackage =
                    Docx4J.load(new File("E:/file/任务书-陈岐望.docx"));

            File file = new File("E:/file/任务.pdf");
            FileOutputStream stream = new FileOutputStream(file);
            FOSettings foSettings = new FOSettings(mlPackage);

            FopFactoryBuilder fopFactoryBuilder = FORendererApacheFOP.getFopFactoryBuilder(foSettings);
            FopFactory fopFactory = fopFactoryBuilder.build();
            FOUserAgent foUserAgent = FORendererApacheFOP.getFOUserAgent(foSettings, fopFactory);
            foUserAgent.setTitle("my title");

            stream.close();
        } catch (Docx4JException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FOPException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
