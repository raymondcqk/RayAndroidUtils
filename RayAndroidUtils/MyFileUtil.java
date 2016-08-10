
/**
 * Created by 陈其康 raymondchan on 2016/8/9 0009.
 * 这是一个File操作的工具类
 * 均为静态方法
 */
public class MyFileUtil {
    public static final String CHAR_DECODE_UTF8 = "UTF-8";

    /**
     * 读取内置存储/data/data/packagename/files/目录下的文件
     *
     * @param context  上下文，传this
     * @param filename 文件名
     * @return 返回一个字符串
     */
    public static String readInternalFile(Context context, String filename) {
        BufferedReader reader = null;
        FileInputStream in = null;
        StringBuilder builder = new StringBuilder();
        String line = "";
        try {
            in = context.openFileInput(filename);
            reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return builder.toString();
    }

    /**
     * 写入内置存储/data/data/packagename/files/目录下的文件
     *
     * @param context
     * @param filename
     * @param mode     操作模式 Context.MODE_PRIVATE 覆盖|Context.MODE_APPEND 追加
     * @param data     数据内容
     */
    public static void writeInternalFile(Context context, String filename, int mode, String data) {
        FileOutputStream out;
        OutputStreamWriter streamWriter;
        BufferedWriter writer = null;
        try {
            out = context.openFileOutput(filename, mode);
            streamWriter = new OutputStreamWriter(out);
            writer = new BufferedWriter(streamWriter);
            writer.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 写入 在外部存储器根目录下
     *
     * @param filename 文件名(带后缀)
     * @param data     数据内容
     * @param isAppend 是否为追加模式 true 追加 | false 覆盖
     */
    public static void writeExternalFile(String filename, String data, Boolean isAppend) {
        //filename记得写后缀，否则无法在sd上创建
        File file = new File(Environment.getExternalStorageDirectory(), filename);
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = new FileOutputStream(file, isAppend);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
            Log.i("Test", "write external");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 读取 外置存储器根目录下的文件
     *
     * @param filename 文件名
     * @return 字符串
     */
    public static String readExternalFile(String filename) {
        File file = new File(Environment.getExternalStorageDirectory(), filename);
        FileInputStream fis = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        String line = "";
        try {
            fis = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(fis));
            if ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }
}
