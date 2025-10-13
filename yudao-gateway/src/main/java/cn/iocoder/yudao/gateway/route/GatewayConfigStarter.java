//package cn.iocoder.yudao.gateway.route;
//
//
//import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
//import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
//import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
//import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
//import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
//import com.alibaba.csp.sentinel.slots.system.SystemRule;
//import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.config.GatewayProperties;
//import org.springframework.cloud.gateway.filter.factory.AddRequestHeaderGatewayFilterFactory;
//import org.springframework.cloud.gateway.route.Route;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import reactor.core.publisher.Flux;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//
//@Configuration
//public class GatewayConfigStarter implements RouteDefinitionLocator {
//    private static Logger log = LoggerFactory.getLogger("GatewayConfigStarter");
//
////    private static final String MAX_AGE = "600L";
////
////    public static String SENTINEL_RULES_CACHE_KEY = "sentinel:rules:gateway";
//
//
////    @ExceptionHandler(WebClientResponseException.class)
////    public ResponseEntity<String> handleException(WebClientResponseException ex){
////        return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
////    }
//
//
////    @Autowired
////    IgnoreCookieGatewayFilterFactory ignoreCookie;
////    @Autowired
////    JwtCheckGatewayFilterFactory jwtCheck;
////    @Autowired
////    IgnoreLimitGatewayFilterFactory ignoreLimit;
////    / **
////     * 获取网关路由定义,动态创建路由入口
////      * @return
////      */
////    @Bean
////    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AddRequestHeaderGatewayFilterFactory throttle) {
//////        if(WhoAmI.getGwUint() == null){
//////            log.error("启动脚本需要配置网关单元类型 ule.gwunit,具体数据请联系开发人员");
//////            throw new RuntimeException("启动脚本需要配置网关单元类型 ule.gwunit,具体数据请联系开发人员");
//////        }
////
////        return assembleRouteLocatorBuilder(builder, throttle);
////    }
////    private static final String GENKEY_0 = "_genkey_0";
////    private static final String GENKEY_1 = "_genkey_1";
//
////    private RouteLocator assembleRouteLocatorBuilder(RouteLocatorBuilder builder, AddRequestHeaderGatewayFilterFactory throttle){
//////        List<RouteDefinition> routeDefinitionList ;
////        RouteLocatorBuilder.Builder builder1 = builder.routes();
////        try {
////            routeDefinitionList = reloadRouteDefinitions();
////        }catch (Exception e){
////            log.error(e.getMessage());
////            return builder1.build();
////        }
////        for(RouteDefinition routeDefinition : routeDefinitionList){
////            builder1.route(routeDefinition.getId(),(PredicateSpec r) -> predicateSpec(r,routeDefinition)
////                    .filters(f->{
////                        if (!routeDefinition.getFilters().isEmpty()) {
////                            for (FilterDefinition filterDefinition : routeDefinition.getFilters()) {
////                                if ("AddRequestHeader".equals(filterDefinition.getName())) {
////                                    f.addRequestHeader(filterDefinition.getArgs().get(GENKEY_0),filterDefinition.getArgs().get(GENKEY_1));
////                                }else if ("RemoveRequestHeader".equals(filterDefinition.getName())) {
////                                    f.removeRequestHeader(filterDefinition.getArgs().get(GENKEY_0));
////                                }else if ("AddResponseHeader".equals(filterDefinition.getName())) {
////                                    f.addResponseHeader(filterDefinition.getArgs().get(GENKEY_0),filterDefinition.getArgs().get(GENKEY_1));
////                                }else if ("RemoveResponseHeader".equals(filterDefinition.getName())) {
////                                    f.removeResponseHeader(filterDefinition.getArgs().get(GENKEY_0));
////                                }else if ("AddRequestParameter".equals(filterDefinition.getName())) {
////                                    f.addRequestParameter(filterDefinition.getArgs().get(GENKEY_0),filterDefinition.getArgs().get(GENKEY_1));
////                                }else if ("RemoveRequestParameter".equals(filterDefinition.getName())) {
////                                    f.removeRequestParameter(filterDefinition.getArgs().get(GENKEY_0));
////                                }else if ("Redirect".equals(filterDefinition.getName())) {
////                                    f.redirect(HttpStatus.MOVED_PERMANENTLY.value(),filterDefinition.getArgs().get(GENKEY_1));
////                                }else if ("StripPrefix".equals(filterDefinition.getName())) {
////                                    if(StringUtils.hasLength(filterDefinition.getArgs().get(GENKEY_0)))
////                                        f.stripPrefix(Integer.valueOf(filterDefinition.getArgs().get(GENKEY_0)));
////                                }else if ("RewritePath".equals(filterDefinition.getName())) {
////                                    f.rewritePath(filterDefinition.getArgs().get(GENKEY_0),filterDefinition.getArgs().get(GENKEY_1));
////                                }else if ("Hystrix".equals(filterDefinition.getName())) {
////                                    //                                    if(StringUtils.isNotEmpty(filterDefinition.getArgs().get(_GENKEY_0)))
//////                                    f.hystrix(config -> config.setFallbackUri("forward:app/fallback").setName("myHystrixCmd"));
////                                }else if ("Retry".equals(filterDefinition.getName())) {
////                                    if(StringUtils.hasLength(filterDefinition.getArgs().get(GENKEY_0)))
////                                        f.retry(Integer.valueOf(filterDefinition.getArgs().get(GENKEY_0)));
////                                }else if ("SetPath".equals(filterDefinition.getName())) {
////                                    f.setPath(filterDefinition.getArgs().get(GENKEY_0));
////                                }else if ("PrefixPath".equals(filterDefinition.getName())) {
////                                    f.prefixPath(filterDefinition.getArgs().get(GENKEY_0));
//////                                }else if ("IgnoreCookieAuth".equals(filterDefinition.getName())) {
//////                                    AbstractNameValueGatewayFilterFactory.NameValueConfig config = new AbstractNameValueGatewayFilterFactory.NameValueConfig();
//////                                    config.setName("IgnoreCookie");
//////                                    f.filter(ignoreCookie.apply(config));
//////                                }else if ("JwtCheck".equals(filterDefinition.getName())) {
//////                                    AbstractNameValueGatewayFilterFactory.NameValueConfig config = new AbstractNameValueGatewayFilterFactory.NameValueConfig();
//////                                    config.setName("JwtCheck");
//////                                    f.filter(jwtCheck.apply(config));
//////                                }else if ("IgnoreLimit".equals(filterDefinition.getName())) {
//////                                    AbstractNameValueGatewayFilterFactory.NameValueConfig config = new AbstractNameValueGatewayFilterFactory.NameValueConfig();
//////                                    config.setName("IgnoreLimit");
//////                                    f.filter(ignoreLimit.apply(config));
////                                }
////                            }
////                        }
////                        //                        f.hystrix(config -> config.setFallbackUri("forward:/app/fallback").setName("myHystrixCmd"));
////                        return f;
////                    })
////                    .uri(routeDefinition.getUri()));
////        }
////        return builder1.build();
////    }
////    private final static String REGEXP = "regexp";
////    private final static String PATTERN = "pattern";
//
////    @Value("${basecfg.service}")
////    private String basecfgService;
////    private BooleanSpec predicateSpec(PredicateSpec predicateSpec,RouteDefinition routeDefinition){
////        String path = null;
////        predicateSpec.readBody(String.class,body->true);
////        for(PredicateDefinition predicateDefinition : routeDefinition.getPredicates()){
////            if("Path".equals(predicateDefinition.getName())){
////                path = predicateDefinition.getArgs().get(PATTERN);
////            }else if("Before".equals(predicateDefinition.getName())){
////                String before = predicateDefinition.getArgs().get("datetime");
////                if(StringUtils.hasLength(before)){
////                    try {
////                        ZonedDateTime zonedDateTime = ZonedDateTime.parse(before);
////                        predicateSpec.before(zonedDateTime);
////                    }catch (Exception e){
////                        log.error(e.getMessage());
////                    }
////                }
////            }else if("Header".equals(predicateDefinition.getName())){
////                if(predicateDefinition.getArgs().get(REGEXP) != null){
////                    predicateSpec.header(predicateDefinition.getArgs().get("header"), predicateDefinition.getArgs().get(REGEXP));
////                }
////            }else if("Query".equals(predicateDefinition.getName())){
////                if(predicateDefinition.getArgs().get(REGEXP) != null){
////                    predicateSpec.query(predicateDefinition.getArgs().get("param"), predicateDefinition.getArgs().get(REGEXP));
////                }else{
////                    predicateSpec.query(predicateDefinition.getArgs().get("param"));
////                }
////            }else if("Weight".equals(predicateDefinition.getName())){
////                if(StringUtils.hasLength(predicateDefinition.getArgs().get(GENKEY_1))){
////                    predicateSpec.weight(predicateDefinition.getArgs().get(GENKEY_0), Integer.valueOf(predicateDefinition.getArgs().get(GENKEY_1)));
////                }
////            }else if("Method".equals(predicateDefinition.getName())){
////                if(predicateDefinition.getArgs().get("method") != null){
////                    predicateSpec.method(predicateDefinition.getArgs().get("method"));
////                }
////            }else if("After".equals(predicateDefinition.getName())){
////                String after = predicateDefinition.getArgs().get("datetime");
////                if(StringUtils.hasLength(after)){
////                    try {
////                        ZonedDateTime zonedDateTime = ZonedDateTime.parse(after);
////                        predicateSpec.after(zonedDateTime);
////                    }catch (Exception e){
////                        log.error(e.getMessage());
////                    }
////                }
////            }else if("Host".equals(predicateDefinition.getName())){
////                if(predicateDefinition.getArgs().get(PATTERN) != null){
////                    predicateSpec.host(predicateDefinition.getArgs().get(PATTERN).split(","));
////                }
////            }else if("Cookie".equals(predicateDefinition.getName())){
////                if(predicateDefinition.getArgs().get(REGEXP) != null){
////                    predicateSpec.cookie(predicateDefinition.getArgs().get("name"),predicateDefinition.getArgs().get(REGEXP));
////                }
////            }else if("Between".equals(predicateDefinition.getName())){
////                String datetime1 = predicateDefinition.getArgs().get("datetime1");
////                String datetime2 = predicateDefinition.getArgs().get("datetime2");
////                if(StringUtils.hasLength(datetime1)){
////                    try {
////                        ZonedDateTime zonedDateTime = ZonedDateTime.parse(datetime1);
////                        ZonedDateTime zonedDateTime2 = ZonedDateTime.parse(datetime2);
////                        predicateSpec.between(zonedDateTime,zonedDateTime2);
////                    }catch (Exception e){
////                        log.error(e.getMessage());
////                    }
////                }
////            }
////        }
////        predicateSpec.order(Integer.parseInt(routeDefinition.getId()));
////        return predicateSpec.path(path);
////    }
////    private static List<String> routeIds = new ArrayList<>();
////    private List<RouteDefinition> reloadRouteDefinitions() {
////        try {
////            List<RouteDefinition> routesJson = new ArrayList<>();
////            log.info("init gateway routes {}", WhoAmI.getGwUint());
////            String result = OkhttpUtil.post(basecfgService + "/basecfgmgr/gateway/getAllGatewayConfig?groupName="+WhoAmI.getGwUint(), null, null);
////            ResultDTO<Object> resultDTO = JSON.parseObject(result, ResultDTO.class);
////            if (resultDTO != null && resultDTO.getCode().equals("0000")) {
////                JSONArray array = (JSONArray) resultDTO.getData();
////                log.info("get gateway routes {}", array.size());
////                for (Object object : array) {
////                    JSONObject jsonObject = (JSONObject) object;
////                    GatewayRouteDefinition gateway = new GatewayRouteDefinition();
////                    gateway.setId(jsonObject.getString("id"));
////                    List<GatewayFilterDefinition> flist = JSON.parseArray(jsonObject.getString("filters"), GatewayFilterDefinition.class);
////                    gateway.setFilters(flist);
////                    String predicates = jsonObject.getString("predicates");
////                    List<GatewayPredicateDefinition> plist = JSON.parseArray(predicates, GatewayPredicateDefinition.class);
////                    gateway.setPredicates(plist);
////                    gateway.setOrder(jsonObject.getIntValue("sort"));
////                    gateway.setUri(jsonObject.getString("uri"));
////                    gateway.setStatus(jsonObject.getIntValue("status"));
////                    routesJson.add(CustomizedRouteUtil.assembleRouteDefinition(gateway));
////                }
////                return routesJson;
////            } else {
////                log.info("gateway route init failed");
////                throw new CustomizedException(1, "获取初始化路由数据失败");
////            }
////        }finally {
////            // 异步初始化路由熔断规则
////            String env = WhoAmI.getEnv();
////            if ("prd".equals(env)) {
////                new Thread(() -> initSentinelRulesFromRedis(routeIds)).start();
////            }
////        }
////    }
//
////    @Bean
////    @LoadBalanced
////    public RestTemplate restTemplage() {
////        return new RestTemplate();
////    }
//    private static final  String DEGRADE = "degrade";
//
//    public static void initSentinelRulesFromRedis(List<String> rIds){
//        Map<String,String> rulesMap = new HashMap<>();//CustomizedCacheCloudClientUtil.getMap(SENTINEL_RULES_CACHE_KEY);
////        log.info("initSentinelRulesFromRedis rules {}",rulesMap);
//        List<DegradeRule> degradeRules = new ArrayList<>();
//        if(rulesMap != null && rulesMap.get(DEGRADE) != null){
//            degradeRules.addAll(com.alibaba.fastjson.JSON.parseArray(rulesMap.get(DEGRADE), DegradeRule.class));
//        }
//        if(rIds != null) {
//            rIds.forEach(r -> {
//                if (!degradeRules.isEmpty()) {
//                    List<DegradeRule> dList = degradeRules.stream().filter(t -> r.equals(t.getResource())).toList();
//                    if (dList.isEmpty()) {
//                        degradeRules.addAll(initSentinelByResourceName(r));
//                    }
//                } else {
//                    degradeRules.addAll(initSentinelByResourceName(r));
//                }
//            });
//        }
//        try {
//            DegradeRuleManager.loadRules(degradeRules);
//        } catch (Exception e) {
//            log.error("路由熔断规则加载异常 degradeRules {}", degradeRules);
//        }
//        systemRulesInit(rulesMap);
//
//        loadSentinelRules(rulesMap);
//    }
//
//    private static void systemRulesInit(Map<String, String> rulesMap) {
//        if(rulesMap == null || rulesMap.get("system") == null) {
//            List<SystemRule> systemRules = new ArrayList<>();
//            try {
//                SystemRule systemRule = new SystemRule();
//                systemRule.setHighestCpuUsage(0.8);
//                systemRules.add(systemRule);
//                SystemRuleManager.loadRules(systemRules);
//            } catch (Exception e) {
//                log.error("系统规则加载异常 systemRules {}", systemRules);
//            }
//        }
//    }
//
//    private static void loadSentinelRules(Map<String, String> rulesMap) {
//        if (rulesMap != null && !rulesMap.isEmpty()) {
//            rulesMap.forEach((k, v)->{
//                if ("flow".equalsIgnoreCase(k)) {
//                    List<FlowRule> rules = com.alibaba.fastjson.JSON.parseArray(v, FlowRule.class);
//                    FlowRuleManager.loadRules(rules);
//                    log.info("FlowRuleManager.loadRules(rules) success rules {}",rules);
//                } else if ("authority".equalsIgnoreCase(k)) {
//                    List<AuthorityRule> rules = com.alibaba.fastjson.JSON.parseArray(v, AuthorityRule.class);
//                    AuthorityRuleManager.loadRules(rules);
//                    log.info("AuthorityRuleManager.loadRules(rules) success rules {}",rules);
//                } else if (DEGRADE.equalsIgnoreCase(k)) {
//                    List<DegradeRule> rules = com.alibaba.fastjson.JSON.parseArray(v, DegradeRule.class);
//                    DegradeRuleManager.loadRules(rules);
//                    log.info("DegradeRuleManager.loadRules(rules) success rules {}",rules);
//                } else if ("system".equalsIgnoreCase(k)) {
//                    List<SystemRule> rules = com.alibaba.fastjson.JSON.parseArray(v, SystemRule.class);
//                    SystemRuleManager.loadRules(rules);
//                    log.info("SystemRuleManager.loadRules(rules) success rules {}",rules);
//                }
//            });
//        }
//    }
//
//    /**
//     * 初始化网关流控规则
//     */
//    public static List<DegradeRule> initSentinelByResourceName(String resource) {
//        List<DegradeRule> rules = new ArrayList<>();
//        try {
//            // ---------------熔断-降级配置-------------// resource 资源名称
//
//            DegradeRule degradeRule = new DegradeRule(resource)
//                    // 异常比率模式(秒级)
//                    .setGrade(CircuitBreakerStrategy.ERROR_RATIO.getType())
//                    // 异常比率阈值(50%)
//                    .setCount(0.5)
//                    .setMinRequestAmount(100)
//                    // 熔断降级时间(10s)
//                    .setTimeWindow(5)
//                    .setStatIntervalMs(5000);
//            DegradeRule rule = new DegradeRule(resource)
//                    // 慢调用比例，设置5s为慢调用
//                    .setGrade(CircuitBreakerStrategy.SLOW_REQUEST_RATIO.getType())
//                    // Max allowed response time
//                    .setCount(3000)
//                    // Retry timeout (in second)
//                    .setTimeWindow(2)
//                    // Circuit breaker opens when slow request ratio > 60%
//                    .setSlowRatioThreshold(0.6)
//                    .setMinRequestAmount(300)
//                    .setStatIntervalMs(10000);
//            rules.add(rule);
//            rules.add(degradeRule);
//        }catch (Exception e){
//            log.error("路由熔断规则加载异常 routeId = {}",resource);
//        }
//        return rules;
//    }
//
//    private GatewayProperties gatewayProperties;
//
//    public GatewayConfigStarter(GatewayProperties gatewayProperties) {
//        this.gatewayProperties = gatewayProperties;
//    }
//    @Override
//    public Flux<RouteDefinition> getRouteDefinitions() {
//        List<RouteDefinition> routeDefinitions = gatewayProperties.getRoutes();
//        List<String> routeIds = new ArrayList<>();
//        routeDefinitions.forEach(routeDefinition -> routeIds.add(routeDefinition.getId()));
//        initSentinelRulesFromRedis(routeIds);// 初始化熔断规则
//        return Flux.fromIterable(gatewayProperties.getRoutes());
//    }
//}
