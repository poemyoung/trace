package com.trace.service.test;

import com.trace.api.openid.TencentPosService;
import com.trace.dao.entity.*;
import com.trace.dao.repository.*;
import com.trace.service.address.AddrService;
import com.trace.service.address.UserAndAddrService;
import com.trace.service.entity.recentity.Pos;
import com.trace.service.redis.CacheNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xzp
 * Created on 2021/2/26
 */
@Service
@CacheConfig(cacheNames = "trace")
public class TestMysqlUsableService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    TestMysqlUsableMapper mapper;
    String sData = Calendar.getInstance().get(Calendar.MONTH) + "月" +
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "日";
    Integer iData = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

    public boolean isMysqlAvaInsert() {
        TestMysqlUsable usable = new TestMysqlUsable();
        usable.setDataVarchar(sData);
        usable.setDataInt(iData);
        mapper.insert(usable);
        TestMysqlUsableExample example = new TestMysqlUsableExample();
        TestMysqlUsableExample.Criteria criteria = example.createCriteria();
        criteria.andDataIntIsNotNull().andDataVarcharIsNotNull();
        List<TestMysqlUsable> testMysqlUsables = mapper.selectByExample(example);
        return !testMysqlUsables.isEmpty();
    }

    @Cacheable(value = "addr_cache",key="#key")
    public boolean setRedisKey(String key,String value) {
        System.out.println("执行了一次");
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value);
        return true;
    }
    public String getRedisKey(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return (String)valueOperations.get(key);

    }


    @Autowired
    UserMapper userInfoMapper;
    @Autowired
    UserDetailMapper userDetailMapper;
    @Autowired
    UserConvertMapper userCvtMapper;



    public void addRandom(int userId) {
          Random random = new Random();
          int range = random.nextInt(400);
          RandomDataGenerator generator = new RandomDataGenerator();
          User user = new User();
          user.setId(userId);
          user.setName(generator.randomName());
          user.setCardId(generator.randomIdCard());
          userInfoMapper.insertSelective(user);
          UserDetail detail = new UserDetail();
          detail.setIduserDetail(userId);
          detail.setPhone(generator.randomTel());
          detail.setRiskFlag(0);
          detail.setTemp(RandomDataGenerator.randomTemp());
          int i =  new RandomDataGenerator().getNum(addrId,addrId+range);
          detail.setAddrId(i);
          userDetailMapper.insertSelective(detail);
          UserConvert cvt = new UserConvert();
          cvt.setUserId(userId);
          cvt.setOpenId("oSjSB4"+generator.randomString("gip9HyNGtP-poJep0aNl4E".length()));
          userCvtMapper.insertSelective(cvt);
          addRandomAddr(range,userId);
    }

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    AddrService service;
    @Autowired
    UserAndAddrService uaaService;

    int addrId = 1;

    public void addRandomAddr(int num,int user){
        int old = addrId;
        addrId += num;
        for(int i = old;i < addrId;i++) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            Pos location = new Pos();
                            Map<String, String> map = new RandomDataGenerator().randomLonLatChina();
                            location.setLatitude(Float.parseFloat(map.get("W")));
                            location.setLongitude(Float.parseFloat(map.get("J")));
                            location.setUserId(user + "");
                            int addrId = service.addrInsert(location.getLatitude(), location.getLongitude(), user);
                            boolean f = uaaService.addUARelate(user, addrId);
                        }
                    }
            ).start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class RandomDataGenerator {
        Random random = new Random();
        public String randomName() {
            String[] Surname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
                    "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
                    "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷",
                    "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
                    "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
                    "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季"};
            String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣" +
                    "爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨" +
                    "瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸" +
                    "菲寒伊亚宜可姬舒影荔枝思丽摹本无关风月我题序等你回情字何解怎落笔都不对而我独缺你" +
                    "一生的了解";
            String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广" +
                    "志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信" +
                    "子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思" +
                    "群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若" +
                    "鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘" +
                    "情字毯子岁月青石板街回眸一笑你婉约恨了没而";
            String a = Surname[random.nextInt(Surname.length)];
            char b;
            char c;
            boolean sex = random.nextBoolean();
            if(sex) {
                 b = boy.charAt(random.nextInt(boy.length()));
                 c = boy.charAt(random.nextInt(boy.length()));
            }else {
                b = girl.charAt(random.nextInt(girl.length()));
                c = girl.charAt(random.nextInt(girl.length()));
            }
            return a+b+c;
        }

        public String randomIdCard() {
            String id = "";
            // 随机生成省、自治区、直辖市代码 1-2
            String provinces[] = { "11", "12", "13", "14", "15", "21", "22", "23",
                    "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
                    "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
                    "63", "64", "65", "71", "81", "82" };
            String province = provinces[new Random().nextInt(provinces.length - 1)];
            // 随机生成地级市、盟、自治州代码 3-4
            String citys[] = { "01", "02", "03", "04", "05", "06", "07", "08",
                    "09", "10", "21", "22", "23", "24", "25", "26", "27", "28" };
            String city = citys[new Random().nextInt(citys.length - 1)];
            // 随机生成县、县级市、区代码 5-6
            String countys[] = { "01", "02", "03", "04", "05", "06", "07", "08",
                    "09", "10", "21", "22", "23", "24", "25", "26", "27", "28",
                    "29", "30", "31", "32", "33", "34", "35", "36", "37", "38" };
            String county = countys[new Random().nextInt(countys.length - 1)];
            // 随机生成出生年月 7-14
            SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
            Date beginDate = new Date();
            Calendar date = Calendar.getInstance();
            date.setTime(beginDate);
            date.set(Calendar.DATE,
                    date.get(Calendar.DATE) - new Random().nextInt(365 * 100));
            String birth = dft.format(date.getTime());
            // 随机生成顺序号 15-17
            String no = new Random().nextInt(999) + "";
            // 随机生成校验码 18
            String checks[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                    "X" };
            String check = checks[new Random().nextInt(checks.length - 1)];
            // 拼接身份证号码
            id = province + city + county + birth + no + check;

            return id;
        }

        private  String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
        public  String randomTel() {
            int index=getNum(0,telFirst.length-1);
            String first=telFirst[index];
            String second=String.valueOf(getNum(1,888)+10000).substring(1);
            String third=String.valueOf(getNum(1,9100)+10000).substring(1);
            return first+second+third;
        }

        public int getNum(int start,int end) {
            return (int)(Math.random()*(end-start+1)+start);
        }

        public  String randomString(int length){
            String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNO-PQRSTUVWXYZ0123456789";
            Random random=new Random();
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<length;i++){
                int number=random.nextInt(str.length()-1);
                sb.append(str.charAt(number));
            }
            return sb.toString();
        }

        public  Map<String, String> randomLonLatChina() {
            return this.randomLonLat(90,128,20,53);
        }

        public  Map<String, String> randomLonLat(double MinLon, double MaxLon, double MinLat, double MaxLat) {
            BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
            String lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();// 小数后6位
            db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
            String lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();
            Map<String, String> map = new HashMap<String, String>();
            map.put("J", lon);
            map.put("W", lat);
            return map;
        }

        public static String randomTemp() {
            return (35 + Math.random() * 3+"").substring(0,4);
        }

    }

}
