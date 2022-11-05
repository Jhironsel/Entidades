package sur.softsurena.jfilechooser;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ImageFilter extends FileFilter {
    final static String jpeg = "jpeg";
    final static String jpg = "jpg";
    final static String gif = "gif";
    final static String tiff = "tiff";
    final static String tif = "tif";
    
    // Accept all directories and all gif, jpg, or tiff files.
    public boolean accept(File f) {

        if (f.isDirectory()) {
            return true;
        }

        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            String extension = s.substring(i+1).toLowerCase();
            if (tiff.equals(extension) ||
                tif.equals(extension) ||
                gif.equals(extension) ||
                jpeg.equals(extension) ||
                jpg.equals(extension)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }
    
    // The description of this filter
    public String getDescription() {
        return "Just Images";
    }
}
