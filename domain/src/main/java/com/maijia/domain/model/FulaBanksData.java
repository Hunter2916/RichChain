package com.maijia.domain.model;

import java.util.List;

/**
 * Created by 2017/10/18.
 */

public class FulaBanksData {


    /**
     * code : 0
     * msg : null
     * data : [{"bank":"中国工商银行","id":1},{"bank":"中国农业银行","id":2},{"bank":"中国银行","id":3},{"bank":"中国建设银行","id":4},{"bank":"国家开发银行","id":5},{"bank":"中国进出口银行","id":6},{"bank":"中国农业发展银行","id":7},{"bank":"交通银行","id":8},{"bank":"中国邮政储蓄银行","id":9},{"bank":"中信银行","id":10},{"bank":"中国光大银行","id":11},{"bank":"华夏银行","id":12},{"bank":"中国民生银行","id":13},{"bank":"广东发展银行","id":14},{"bank":"中国人民银行","id":15},{"bank":"招商银行","id":16},{"bank":"上海浦东发展银行","id":18},{"bank":"城市商业银行","id":19},{"bank":"农村商业银行","id":20},{"bank":"恒丰银行","id":21},{"bank":"农村合作银行","id":22},{"bank":"渤海银行","id":23},{"bank":"徽商银行","id":24},{"bank":"城市信用社","id":25},{"bank":"农村信用联社","id":26},{"bank":"香港上海汇丰银行","id":27},{"bank":"东亚银行","id":28},{"bank":"南洋商业银行","id":29},{"bank":"恒生银行","id":30},{"bank":"(香港地区)银行","id":32},{"bank":"集友银行","id":33},{"bank":"星展银行（香港）","id":34},{"bank":"永亨银行","id":35},{"bank":"花旗银行（中国）","id":36},{"bank":"美国银行","id":37},{"bank":"美国摩根大通银行","id":38},{"bank":"日本三菱东京日联银行","id":39},{"bank":"日本日联银行","id":40},{"bank":"日本三井住友银行","id":41},{"bank":"日本瑞穗实业银行","id":42},{"bank":"日本山口银行","id":43},{"bank":"韩国外换银行","id":44},{"bank":"韩国新韩银行","id":45},{"bank":"韩国友利银行","id":46},{"bank":"韩国产业银行","id":47},{"bank":"韩国中小企业银行","id":48},{"bank":"新加坡星展银行","id":49},{"bank":"奥地利中央合作银行","id":50},{"bank":"比利时联合银行","id":51},{"bank":"荷兰银行","id":52},{"bank":"荷兰商业银行","id":53},{"bank":"渣打银行（中国）","id":54},{"bank":"法国兴业银行","id":55},{"bank":"法国巴黎银行","id":56},{"bank":"法国东方汇理银行","id":57},{"bank":"德国德累斯登银行","id":58},{"bank":"德意志银行","id":59},{"bank":"德国商业银行","id":60},{"bank":"德国西德银行","id":61},{"bank":"德国巴伐利亚州银行","id":62},{"bank":"瑞士信贷银行","id":63},{"bank":"加拿大蒙特利尔银行","id":64},{"bank":"澳大利亚和新西兰银行集团","id":65},{"bank":"德富泰银行","id":66},{"bank":"厦门国际银行","id":67},{"bank":"法国巴黎银行（中国）","id":68},{"bank":"平安银行","id":69},{"bank":"青岛国际银行","id":70},{"bank":"富邦华一银行","id":71},{"bank":"大连银行","id":72},{"bank":"上海银行","id":73},{"bank":"浙商银行","id":74},{"bank":"宁波银行","id":75},{"bank":"杭州银行","id":76},{"bank":"江苏银行","id":77},{"bank":"兰州银行","id":78},{"bank":"南昌银行","id":79},{"bank":"南京银行","id":80},{"bank":"齐鲁银行","id":81},{"bank":"齐商银行","id":82},{"bank":"天津银行","id":83},{"bank":"温州银行","id":84},{"bank":"西安银行","id":85},{"bank":"厦门银行","id":86},{"bank":"鄞州银行","id":87},{"bank":"台州银行","id":88},{"bank":"广州银行","id":89},{"bank":"临商银行","id":90},{"bank":"嘉兴银行","id":91},{"bank":"金华银行","id":92},{"bank":"青岛银行","id":93},{"bank":"南阳银行","id":94},{"bank":"成都银行","id":95},{"bank":"深圳发展银行","id":96},{"bank":"兴业银行","id":97},{"bank":"北京银行","id":98},{"bank":"广州农村商业银行","id":99},{"bank":"创兴银行","id":100},{"bank":"大众银行（香港）","id":101},{"bank":"上海商业银行","id":102},{"bank":"永隆银行","id":103},{"bank":"大新银行（中国）","id":104},{"bank":"中信银行国际（中国）","id":105},{"bank":"华南商业银行","id":106},{"bank":"彰化商业银行","id":107},{"bank":"合作金库商业银行","id":109},{"bank":"国泰世华商业银行","id":110},{"bank":"第一商业银行","id":111},{"bank":"台湾土地银行","id":112},{"bank":"兆丰国际商业银行","id":113},{"bank":"台湾银行","id":114},{"bank":"玉山银行（中国）","id":115},{"bank":"日本三井住友信托银行","id":116},{"bank":"日本横滨银行","id":117},{"bank":"韩亚银行（中国）","id":118},{"bank":"国民银行（中国）","id":119},{"bank":"台湾中小企业银行","id":120},{"bank":"马来西亚马来亚银行","id":121},{"bank":"首都银行（中国）","id":122},{"bank":"华侨银行（中国）","id":123},{"bank":"大华银行（中国）","id":124},{"bank":"盘谷银行（中国）","id":125},{"bank":"泰国开泰银行","id":126},{"bank":"英国巴克莱银行","id":127},{"bank":"瑞典商业银行","id":128},{"bank":"瑞典北欧斯安银行","id":129},{"bank":"瑞典银行","id":130},{"bank":"法国外贸银行","id":131},{"bank":"德国北德意志州银行","id":132},{"bank":"中德住房储蓄银行","id":133},{"bank":"意大利裕信银行","id":134},{"bank":"意大利联合圣保罗银行","id":135},{"bank":"瑞士银行（中国）","id":136},{"bank":"加拿大丰业银行","id":137},{"bank":"澳大利亚和新西兰银行（中国）","id":138},{"bank":"澳大利亚西太平洋银行","id":139},{"bank":"俄罗斯外贸银行","id":140},{"bank":"摩根士丹利国际银行（中国）","id":141},{"bank":"华美银行（中国）","id":142},{"bank":"荷兰合作银行","id":143},{"bank":"法国巴黎银行（中国）","id":145},{"bank":"华商银行","id":146},{"bank":"重庆三峡银行","id":147},{"bank":"上海农商银行","id":148},{"bank":"村镇银行","id":149},{"bank":"中国银行（香港）","id":151},{"bank":"比利时富通银行","id":152},{"bank":"英国苏格兰皇家银行","id":153},{"bank":"(澳门地区)银行","id":154},{"bank":"重庆银行","id":155},{"bank":"赣州银行","id":156},{"bank":"九江银行","id":157},{"bank":"洛阳银行","id":158},{"bank":"湖北银行","id":159},{"bank":"汉口银行","id":160},{"bank":"苏州银行","id":161},{"bank":"甘肃银行","id":162},{"bank":"贵州银行","id":163},{"bank":"郑州银行","id":164},{"bank":"长安银行","id":165},{"bank":"开封银行","id":166},{"bank":"长沙银行","id":167},{"bank":"河北银行","id":168},{"bank":"唐山银行","id":169},{"bank":"晋商银行","id":170},{"bank":"吉林银行","id":171},{"bank":"哈尔滨银行","id":172},{"bank":"龙江银行","id":173},{"bank":"宁夏银行","id":174},{"bank":"张家口银行","id":175},{"bank":"江西银行","id":176},{"bank":"贵阳银行","id":177},{"bank":"青海银行","id":178},{"bank":"内蒙古银行","id":179},{"bank":"东莞银行","id":180},{"bank":"中原银行","id":181}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bank : 中国工商银行
         * id : 1
         */

        private String bank;
        private int id;

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "bank='" + bank + '\'' +
                    ", id=" + id +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "FulaBanksData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
