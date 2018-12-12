package getfilename;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * 获取当前目录下所有文件夹的名字
 * @Author: ganfengbao
 * @Date: 2018/12/12 23:19
 * @Version 1.0
 */
public class DirList {
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;

        if (args.length == 0) {
            list = path.list();
        } else {
            list = path.list(new DirFilter(args[0]));
        }

        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}

class DirFilter implements FilenameFilter {

    private Pattern pattern;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
