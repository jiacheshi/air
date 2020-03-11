package cn.sdyk.config;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

/**
 * 字符串分割工具类
 */
@Component(value="strSplit")
public class StrSplit {
    /**
     * 分割方法
     * @param str 字符串
      * @return 数组
     */
    public String[] split(String str){
        String[] sol = str.split(",");
        return sol;
    }

    @Test
    public void aVoid(){
        String str="小红,13332,666,aaa,aa,a,a,a,";
        String[] sol = str.split(",");
        for (int i = 0; i < sol.length; i+=2) {
            System.out.print("第i组"+sol[i]);
            System.out.println(sol[i+1]);
        }

    }
}
