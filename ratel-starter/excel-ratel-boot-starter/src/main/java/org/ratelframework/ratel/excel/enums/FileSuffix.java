package org.ratelframework.ratel.excel.enums;


/**
 * @author whd.java@gmail.com
 * @date 2020/03/21 23:49
 * @apiNote Describe the function of this class in one sentence
 */
public enum FileSuffix {

    /***
     * file suffix up 2003 version
     */
    XLSX(".xlsx"),

    /***
     * file suffix until 2003 version
     */
    XLS(".xls");

    private String name;

    public String getName() {
        return this.name;
    }

    private FileSuffix(String name) {
        this.name = name;
    }
}