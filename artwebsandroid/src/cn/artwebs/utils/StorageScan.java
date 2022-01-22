package cn.artwebs.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 存储区域扫描
 * Created by artwebs on 14/12/12.
 */
public class StorageScan {
    private File rootPath;
    private List<String> list;
    private static StorageScan self;
    private StorageScan(){
        list=new ArrayList<String>();
        rootPath=new File("/mnt");
    }

    public static StorageScan instance(){
        if(self==null)
        {
            self=new StorageScan();
        }
        return self;
    }

    private void getDirList(File path,boolean isAll){
        if(path!=null &&path.isDirectory()){
            if(path.canWrite()&&path.canRead()){
                list.add(path.getAbsolutePath());
                if(!isAll)
                    return ;
            }
            File[] files = path.listFiles();
            if(files!=null){
                for (int i=0;i<files.length;i++)
                    getDirList(files[i],isAll);
            }
        }
    }

    public String scanDir() throws Exception {
        list.clear();
        getDirList(rootPath, false);
        if(list.size()==0)
            throw new Exception("没有存储空间");
        return list.get(0);
    }

    public List<String> scanDirList(){
        list.clear();
        getDirList(rootPath,true);
        return list;
    }

}
