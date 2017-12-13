
var k = 0;
var len = 0;
var firstLoad = true;

App.directive('myRepeatDirective',['$timeout', '$parse', function ($timeout, $parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attr, $scope) {
        	if(scope.$index==2){
		    	k++;
		    } 
            if (k==len && firstLoad==true) {
            	k = 0;
                $timeout(function () {
                	// 当页面加载完开始绘制图表
                    scope.$emit('ngRepeatFinished'); // 事件通知
                });
            }
        }
    }
}])


App.controller('dataMapAccountController', [ '$scope', '$state', '$http',
		'$stateParams', '$location', '$sce', '$q', '$window','$filter',
		function($scope, $state, $http, $stateParams, $location, $sce, $q, $window,$filter) {

			$scope.lineIds = [];
			$scope.mainTitle = '业务实时监控';
			$scope.loading = false;
			$scope.selectOptions = [{"key":"10","value":"10"},{"key":"30","value":"30"}, {"key":"60","value":"60"}];
			$scope.sumTime = $scope.selectOptions[2];
			$scope.startTime = '';
		    $scope.endTime = '';
		    $scope.p99StartTime = '';
		    $scope.dataCountry= [
                {'name':'阿富汗', value: NaN},
                {'name':'安哥拉', value: NaN},
                {'name':'阿尔巴尼亚', value: NaN},
                {'name':'阿联酋', value: NaN},
                {'name':'阿根廷', value: NaN},
                {'name':'亚美尼亚', value: NaN},
                {'name':'法属南半球和南极领地', value: NaN},
                {'name':'澳大利亚', value: NaN},
                {'name':'奥地利', value: NaN},
                {'name':'阿塞拜疆', value: NaN},
                {'name':'布隆迪', value: NaN},
                {'name':'比利时', value: NaN},
                {'name':'贝宁', value: NaN},
                {'name':'布基纳法索', value: NaN},
                {'name':'孟加拉国', value: NaN},
                {'name':'保加利亚', value: NaN},
                {'name':'巴哈马', value: NaN},
                {'name':'波斯尼亚和黑塞哥维那', value: NaN},
                {'name':'白俄罗斯', value: NaN},
                {'name':'伯利兹', value: NaN},
                {'name':'百慕大', value: NaN},
                {'name':'玻利维亚', value: NaN},
                {'name':'巴西', value: NaN},
                {'name':'文莱', value: NaN},
                {'name':'不丹', value: NaN},
                {'name':'博茨瓦纳', value: NaN},
                {'name':'中非', value: NaN},
                {'name':'加拿大', value: NaN},
                {'name':'瑞士', value: NaN},
                {'name':'智利', value: NaN},
                {'name':'中国', value: NaN},
                {'name':'象牙海岸', value: NaN},
                {'name':'喀麦隆', value: NaN},
                {'name':'刚果民主共和国（金）', value: NaN},
                {'name':'刚果共和国', value: NaN},
                {'name':'哥伦比亚', value: NaN},
                {'name':'哥斯达黎加', value: NaN},
                {'name':'古巴', value: NaN},
                {'name':'北塞浦路斯', value: NaN},
                {'name':'塞浦路斯', value: NaN},
                {'name':'捷克', value: NaN},
                {'name':'德国', value: NaN},
                {'name':'吉布提', value: NaN},
                {'name':'丹麦', value: NaN},
                {'name':'多明尼加共和国', value: NaN},
                {'name':'阿尔及利亚', value: NaN},
                {'name':'厄瓜多尔', value: NaN},
                {'name':'埃及', value: NaN},
                {'name':'厄立特里亚', value: NaN},
                {'name':'西班牙', value: NaN},
                {'name':'爱沙尼亚', value: NaN},
                {'name':'埃塞俄比亚', value: NaN},
                {'name':'芬兰', value: NaN},
                {'name':'斐', value: NaN},
                {'name':'福克兰群岛', value: NaN},
                {'name':'法国', value: NaN},
                {'name':'加蓬', value: NaN},
                {'name':'英国', value: NaN},
                {'name':'格鲁吉亚', value: NaN},
                {'name':'加纳', value: NaN},
                {'name':'几内亚', value: NaN},
                {'name':'冈比亚', value: NaN},
                {'name':'几内亚比绍', value: NaN},
                {'name':'赤道几内亚', value: NaN},
                {'name':'希腊', value: NaN},
                {'name':'格陵兰', value: NaN},
                {'name':'危地马拉', value: NaN},
                {'name':'法属圭亚那', value: NaN},
                {'name':'圭亚那', value: NaN},
                {'name':'洪都拉斯', value: NaN},
                {'name':'克罗地亚', value: NaN},
                {'name':'海地', value: NaN},
                {'name':'匈牙利', value: NaN},
                {'name':'印度尼西亚', value: NaN},
                {'name':'印度', value: NaN},
                {'name':'爱尔兰', value: NaN},
                {'name':'伊朗', value: NaN},
                {'name':'伊拉克', value: NaN},
                {'name':'冰岛', value: NaN},
                {'name':'以色列', value: NaN},
                {'name':'意大利', value: NaN},
                {'name':'牙买加', value: NaN},
                {'name':'约旦', value: NaN},
                {'name':'日本', value: NaN},
                {'name':'哈萨克斯坦', value: NaN},
                {'name':'肯尼亚', value: NaN},
                {'name':'吉尔吉斯', value: NaN},
                {'name':'柬埔寨', value: NaN},
                {'name':'韩国', value: NaN},
                {'name':'科索沃', value: NaN},
                {'name':'科威特', value: NaN},
                {'name':'老挝', value: NaN},
                {'name':'黎巴嫩', value: NaN},
                {'name':'利比里亚', value: NaN},
                {'name':'利比亚', value: NaN},
                {'name':'斯里兰卡', value: NaN},
                {'name':'莱索托', value: NaN},
                {'name':'立陶宛', value: NaN},
                {'name':'卢森堡', value: NaN},
                {'name':'拉脱维亚', value: NaN},
                {'name':'摩洛哥', value: NaN},
                {'name':'摩尔多瓦', value: NaN},
                {'name':'马达加斯加', value: NaN},
                {'name':'墨西哥', value: NaN},
                {'name':'马其顿', value: NaN},
                {'name':'马里', value: NaN},
                {'name':'缅甸', value: NaN},
                {'name':'黑山', value: NaN},
                {'name':'蒙古', value: NaN},
                {'name':'莫桑比克', value: NaN},
                {'name':'毛里塔尼亚', value: NaN},
                {'name':'马拉维', value: NaN},
                {'name':'马来西亚', value: NaN},
                {'name':'纳米比亚', value: NaN},
                {'name':'新喀里多尼亚', value: NaN},
                {'name':'尼日尔', value: NaN},
                {'name':'尼日利亚', value: NaN},
                {'name':'尼加拉瓜', value: NaN},
                {'name':'荷兰', value: NaN},
                {'name':'挪威', value: NaN},
                {'name':'尼泊尔', value: NaN},
                {'name':'新西兰', value: NaN},
                {'name':'阿曼', value: NaN},
                {'name':'巴基斯坦', value: NaN},
                {'name':'巴拿马', value: NaN},
                {'name':'秘鲁', value: NaN},
                {'name':'菲律宾', value: NaN},
                {'name':'巴布亚新几内亚', value: NaN},
                {'name':'波兰', value: NaN},
                {'name':'波多黎各', value: NaN},
                {'name':'朝鲜', value: NaN},
                {'name':'葡萄牙', value: NaN},
                {'name':'巴拉圭', value: NaN},
                {'name':'卡塔尔', value: NaN},
                {'name':'罗马尼亚', value: NaN},
                {'name':'俄罗斯', value: NaN},
                {'name':'卢旺达', value: NaN},
                {'name':'西撒哈拉', value: NaN},
                {'name':'沙特阿拉伯', value: NaN},
                {'name':'苏丹', value: NaN},
                {'name':'南苏丹', value: NaN},
                {'name':'塞内加尔', value: NaN},
                {'name':'所罗门群岛', value: NaN},
                {'name':'塞拉利昂', value: NaN},
                {'name':'萨尔瓦多', value: NaN},
                {'name':'索马里兰', value: NaN},
                {'name':'索马里', value: NaN},
                {'name':'塞尔维亚', value: NaN},
                {'name':'苏里南', value: NaN},
                {'name':'斯洛伐克', value: NaN},
                {'name':'斯洛文尼亚', value: NaN},
                {'name':'瑞典', value: NaN},
                {'name':'斯威士兰', value: NaN},
                {'name':'叙利亚', value: NaN},
                {'name':'乍得', value: NaN},
                {'name':'多哥', value: NaN},
                {'name':'泰国', value: NaN},
                {'name':'塔吉克斯坦', value: NaN},
                {'name':'土库曼斯坦', value: NaN},
                {'name':'东帝汶', value: NaN},
                {'name':'特里尼达和多巴哥', value: NaN},
                {'name':'突尼斯', value: NaN},
                {'name':'土耳其', value: NaN},
                {'name':'坦桑尼亚', value: NaN},
                {'name':'乌干达', value: NaN},
                {'name':'乌克兰', value: NaN},
                {'name':'乌拉圭', value: NaN},
                {'name':'美国', value: NaN},
                {'name':'乌兹别克斯坦', value: NaN},
                {'name':'委内瑞拉', value: NaN},
                {'name':'越南', value: NaN},
                {'name':'瓦努阿图', value: NaN},
                {'name':'西岸', value: NaN},
                {'name':'也门', value: NaN},
                {'name':'南非', value: NaN},
                {'name':'赞比亚', value: NaN},
                {'name':'津巴布韦', value: NaN},
                {'name':'萨摩亚', value: NaN},
                {'name':'多米尼加', value: NaN},
                {'name':'多米尼克', value: NaN},
                {'name':'奥兰', value: NaN},
                {'name':'毛里求斯', value: NaN},
                {'name':'塞舌尔', value: NaN},
                {'name':'科摩罗', value: NaN},
                {'name':'刚果', value: NaN},
                {'name':'新加坡', value: NaN},
            ];

            $scope.dataProvince= [{
                name: '北京',
                value: NaN
                },
                {
                    name: '天津',
                    value: NaN
                },
                {
                    name: '上海',
                    value: NaN
                },
                {
                    name: '重庆',
                    value: NaN
                },
                {
                    name: '河北',
                    value: NaN
                },
                {
                    name: '河南',
                    value: NaN
                },
                {
                    name: '云南',
                    value: NaN
                },
                {
                    name: '辽宁',
                    value: NaN
                },
                {
                    name: '黑龙江',
                    value: NaN
                },
                {
                    name: '湖南',
                    value: NaN
                },
                {
                    name: '安徽',
                    value: NaN
                },
                {
                    name: '山东',
                    value: NaN
                },
                {
                    name: '新疆',
                    value: NaN
                },
                {
                    name: '江苏',
                    value: NaN
                },
                {
                    name: '浙江',
                    value: NaN
                },
                {
                    name: '江西',
                    value: NaN
                },
                {
                    name: '湖北',
                    value: NaN
                },
                {
                    name: '广西',
                    value: NaN
                },
                {
                    name: '甘肃',
                    value: NaN
                },
                {
                    name: '山西',
                    value: NaN
                },
                {
                    name: '内蒙古',
                    value: NaN
                },
                {
                    name: '陕西',
                    value: NaN
                },
                {
                    name: '吉林',
                    value: NaN
                },
                {
                    name: '福建',
                    value: NaN
                },
                {
                    name: '贵州',
                    value: NaN
                },
                {
                    name: '广东',
                    value: NaN
                },
                {
                    name: '青海',
                    value: NaN
                },
                {
                    name: '西藏',
                    value: NaN
                },
                {
                    name: '四川',
                    value: NaN
                },
                {
                    name: '宁夏',
                    value: NaN
                },
                {
                    name: '海南',
                    value: NaN
                },
                {
                    name: '台湾',
                    value: NaN
                },
                {
                    name: '香港',
                    value: NaN
                },
                {
                    name: '澳门',
                    value: NaN
                }
            ];
			// datepicker 汉化
			var locale = {
					"applyLabel": "确定",
					"cancelLabel": "清空",
					"fromLabel": "起始时间",
					"toLabel": "结束时间'",
					"daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
					"monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
					'format' : 'YYYY-MM-DD HH:mm:ss',
					"customRangeLabel": "自定义",
					};
			$('input[name="daterange"]').daterangepicker({
				'locale' : locale,
				'timePicker' : true,
				'timePicker24Hour' : true,
				'autoUpdateInput' : false,
				'timePickerSeconds' : true,
				'showDropdowns' : true,
				'ranges' : {
					 '1小时': [moment().subtract(1,'hours').format('YYYY-MM-DD HH:00'), moment().format('YYYY-MM-DD HH:mm')],
			          '3小时': [moment().subtract(3,'hours').format('YYYY-MM-DD HH:00'), moment().format('YYYY-MM-DD HH:mm')],
			          '6小时': [moment().subtract(6,'hours').format('YYYY-MM-DD HH:00'), moment().format('YYYY-MM-DD HH:mm')],
			          '1天': [moment().format('YYYY-MM-DD 00:00'), moment().format('YYYY-MM-DD HH:mm')],
			          '3天': [moment().subtract(2,'days').format('YYYY-MM-DD 00:00'), moment().format('YYYY-MM-DD HH:mm')],
			          '5天': [moment().subtract(4,'days').format('YYYY-MM-DD 00:00'), moment().format('YYYY-MM-DD HH:mm')],
			          '7天' : [moment().subtract(6,'days').format('YYYY-MM-DD 00:00'), moment().format('YYYY-MM-DD HH:mm')]
				}
			});
			var drp = $('input[name="daterange"]').data('daterangepicker');
			
			// daterangepicker 清空的点击时间
			$('input[name="daterange"]').on('cancel.daterangepicker', function(ev, picker) {
			      $(this).val('');
			});
			
			// daterangpicker 确定的点击事件
			$('input[name="daterange"]').on('apply.daterangepicker', function(ev, picker) {
				var startStr = picker.startDate.format('YYYY-MM-DD HH:mm:ss');
				var endStr = picker.endDate.format('YYYY-MM-DD HH:mm:ss');
				$(this).val(startStr+' - '+endStr);
			});

            //将日期格式转化为秒数（不是毫秒）
            function dateStrToSec(str){
                var time_sec = (((new Date(str.replace(/-/g, "/"))).getTime())/1000).toString();
                return time_sec;
            }


            function getParams() {
                if($('input[name="daterange"]').val().replace(/(^\s*)|(\s*$)/g, "")=='') {
                    $scope.startTime = '';
                    $scope.endTime = '';
                }
                else {
                    console.log(drp);
                    $scope.startTime = dateStrToSec(drp.startDate.format('YYYY-MM-DD HH:mm:ss'));
                    $scope.endTime = dateStrToSec(drp.endDate.format('YYYY-MM-DD HH:mm:ss'));

                }
                var params = {
                    "currentPage": $scope.currentPage+"",
                    "pageSize": $scope.pageSize,
                    "startTime": $scope.startTime,
                    "endTime": $scope.endTime,
                };
                return params;
            }

            var myChart = echarts.init(document.getElementById('main'));
            myChart.on('click', function(param) {
                if(param.name == "中国") {
                    $scope.getProvinceMetrics();
                    myChart.setOption(options, true);
                } else {
                    myChart.setOption(option, true);
                }
            });
       var  option = {
                title: {
                    text: '异常IP全球分布图',
                    x: 'center',
                    y: 'top'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: function(params) {
                        var value = (params.value + '').split('.');
                       // console.log(params.name);
                        if(value=='NaN') value=0;
                        return params.seriesName + '<br/>' + params.name + ' : ' + value;
                    }
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    x: 'right',
                    y: 'center',
                    feature: {
                        mark: {
                            show: true
                        },
                        dataView: {
                            show: true,
                            readOnly: false
                        },
                        restore: {
                            show: true
                        },
                        saveAsImage: {
                            show: true
                        }
                    }
                },
                dataRange: {
                    x: 'left',
                    y: 'bottom',
                    splitList: [
                        {start: 1500},
                        {start: 1000, end: 1500},
                        {start: 300, end: 1000},
                        {start: 200, end: 300},
                        {start: 10, end: 200},
                        {end: 10}
                    ],
                    color: ['#8B0000', '#EE4F4D']

                },
                series: [{
                    name: '异常IP全球分布图',
                    type: 'map',
                    mapType: 'world',
                    roam: true,

                    mapLocation: {
                        y: 60
                    },
                    itemStyle: {
                        emphasis: {
                            label: {
                                show: false
                            }
                        },
                        normal: {
                            label: {
                                show: true,
                                position: 'inner',
                                formatter: function(params) {

                                    var value = params.value + '';

                                    if(value == 'NaN' || params.value == 0) {
                                        return '     ';
                                    } else {
                                        return value;
                                    }
                                    /*return value;*/
                                }
                            }
                        }

                    },
                    data:$scope.data,
                    nameMap : {
                                    'Afghanistan':'阿富汗',
                                    'Angola':'安哥拉',
                                    'Albania':'阿尔巴尼亚',
                                    'United Arab Emirates':'阿联酋',
                                    'Argentina':'阿根廷',
                                    'Armenia':'亚美尼亚',
                                    'French Southern and Antarctic Lands':'法属南半球和南极领地',
                                    'Australia':'澳大利亚',
                                    'Austria':'奥地利',
                                    'Azerbaijan':'阿塞拜疆',
                                    'Burundi':'布隆迪',
                                    'Belgium':'比利时',
                                    'Benin':'贝宁',
                                    'Burkina Faso':'布基纳法索',
                                    'Bangladesh':'孟加拉国',
                                    'Bulgaria':'保加利亚',
                                    'The Bahamas':'巴哈马',
                                    'Bosnia and Her.':'波斯尼亚和黑塞哥维那',
                                    'Belarus':'白俄罗斯',
                                    'Belize':'伯利兹',
                                    'Bermuda':'百慕大',
                                    'Bolivia':'玻利维亚',
                                    'Brazil':'巴西',
                                    'Brunei':'文莱',
                                    'Bhutan':'不丹',
                                    'Botswana':'博茨瓦纳',
                                    'Central African Rep.':'中非',
                                    'Canada':'加拿大',
                                    'Switzerland':'瑞士',
                                    'Chile':'智利',
                                    'China':'中国',
                                    'Côte d\'Ivoire':'象牙海岸',
                                    'Cameroon':'喀麦隆',
                                    'Dem. Rep. Congo':'刚果民主共和国（金）',
                                    'Republic of the Congo':'刚果共和国',
                                    'Colombia':'哥伦比亚',
                                    'Costa Rica':'哥斯达黎加',
                                    'Cuba':'古巴',
                                    'Northern Cyprus':'北塞浦路斯',
                                    'Cyprus':'塞浦路斯',
                                    'Czech Rep.':'捷克',
                                    'Germany':'德国',
                                    'Djibouti':'吉布提',
                                    'Denmark':'丹麦',
                                    'Dominican Rep.':'多明尼加共和国',
                                    'Algeria':'阿尔及利亚',
                                    'Ecuador':'厄瓜多尔',
                                    'Egypt':'埃及',
                                    'Eritrea':'厄立特里亚',
                                    'Spain':'西班牙',
                                    'Estonia':'爱沙尼亚',
                                    'Ethiopia':'埃塞俄比亚',
                                    'Finland':'芬兰',
                                    'Fiji':'斐',
                                    'Falkland Is.':'福克兰群岛',
                                    'France':'法国',
                                    'Gabon':'加蓬',
                                    'United Kingdom':'英国',
                                    'Georgia':'格鲁吉亚',
                                    'Ghana':'加纳',
                                    'Guinea':'几内亚',
                                    'Gambia':'冈比亚',
                                    'Guinea-Bissau':'几内亚比绍',
                                    'Eq. Guinea':'赤道几内亚',
                                    'Greece':'希腊',
                                    'Greenland':'格陵兰',
                                    'Guatemala':'危地马拉',
                                    'French Guiana':'法属圭亚那',
                                    'Guyana':'圭亚那',
                                    'Honduras':'洪都拉斯',
                                    'Croatia':'克罗地亚',
                                    'Haiti':'海地',
                                    'Hungary':'匈牙利',
                                    'Indonesia':'印度尼西亚',
                                    'India':'印度',
                                    'Ireland':'爱尔兰',
                                    'Iran':'伊朗',
                                    'Iraq':'伊拉克',
                                    'Iceland':'冰岛',
                                    'Israel':'以色列',
                                    'Italy':'意大利',
                                    'Jamaica':'牙买加',
                                    'Jordan':'约旦',
                                    'Japan':'日本',
                                    'Kazakhstan':'哈萨克斯坦',
                                    'Kenya':'肯尼亚',
                                    'Kyrgyzstan':'吉尔吉斯',
                                    'Cambodia':'柬埔寨',
                                    'Korea':'韩国',
                                    'Kosovo':'科索沃',
                                    'Kuwait':'科威特',
                                    'Lao PDR':'老挝',
                                    'Lebanon':'黎巴嫩',
                                    'Liberia':'利比里亚',
                                    'Libya':'利比亚',
                                    'Sri Lanka':'斯里兰卡',
                                    'Lesotho':'莱索托',
                                    'Lithuania':'立陶宛',
                                    'Luxembourg':'卢森堡',
                                    'Latvia':'拉脱维亚',
                                    'Morocco':'摩洛哥',
                                    'Moldova':'摩尔多瓦',
                                    'Madagascar':'马达加斯加',
                                    'Mexico':'墨西哥',
                                    'Macedonia':'马其顿',
                                    'Mali':'马里',
                                    'Myanmar':'缅甸',
                                    'Montenegro':'黑山',
                                    'Mongolia':'蒙古',
                                    'Mozambique':'莫桑比克',
                                    'Mauritania':'毛里塔尼亚',
                                    'Malawi':'马拉维',
                                    'Malaysia':'马来西亚',
                                    'Namibia':'纳米比亚',
                                    'New Caledonia':'新喀里多尼亚',
                                    'Niger':'尼日尔',
                                    'Nigeria':'尼日利亚',
                                    'Nicaragua':'尼加拉瓜',
                                    'Netherlands':'荷兰',
                                    'Norway':'挪威',
                                    'Nepal':'尼泊尔',
                                    'New Zealand':'新西兰',
                                    'Oman':'阿曼',
                                    'Pakistan':'巴基斯坦',
                                    'Panama':'巴拿马',
                                    'Peru':'秘鲁',
                                    'Philippines':'菲律宾',
                                    'Papua New Guinea':'巴布亚新几内亚',
                                    'Poland':'波兰',
                                    'Puerto Rico':'波多黎各',
                                    'Dem. Rep. Korea':'朝鲜',
                                    'Portugal':'葡萄牙',
                                    'Paraguay':'巴拉圭',
                                    'Qatar':'卡塔尔',
                                    'Romania':'罗马尼亚',
                                    'Russia':'俄罗斯',
                                    'Rwanda':'卢旺达',
                                    'W. Sahara':'西撒哈拉',
                                    'Saudi Arabia':'沙特阿拉伯',
                                    'Sudan':'苏丹',
                                    'S. Sudan':'南苏丹',
                                    'Senegal':'塞内加尔',
                                    'Solomon Is.':'所罗门群岛',
                                    'Sierra Leone':'塞拉利昂',
                                    'El Salvador':'萨尔瓦多',
                                    'Somaliland':'索马里兰',
                                    'Somalia':'索马里',
                                    'Serbia':'塞尔维亚',
                                    'Suriname':'苏里南',
                                    'Slovakia':'斯洛伐克',
                                    'Slovenia':'斯洛文尼亚',
                                    'Sweden':'瑞典',
                                    'Swaziland':'斯威士兰',
                                    'Syria':'叙利亚',
                                    'Chad':'乍得',
                                    'Togo':'多哥',
                                    'Thailand':'泰国',
                                    'Tajikistan':'塔吉克斯坦',
                                    'Turkmenistan':'土库曼斯坦',
                                    'Timor-Leste':'东帝汶',
                                    'Trinidad and Tobago':'特里尼达和多巴哥',
                                    'Tunisia':'突尼斯',
                                    'Turkey':'土耳其',
                                    'Tanzania':'坦桑尼亚',
                                    'Uganda':'乌干达',
                                    'Ukraine':'乌克兰',
                                    'Uruguay':'乌拉圭',
                                    'United States':'美国',
                                    'Uzbekistan':'乌兹别克斯坦',
                                    'Venezuela':'委内瑞拉',
                                    'Vietnam':'越南',
                                    'Vanuatu':'瓦努阿图',
                                    'West Bank':'西岸',
                                    'Yemen':'也门',
                                    'South Africa':'南非',
                                    'Zambia':'赞比亚',
                                    'Zimbabwe':'津巴布韦',
                                    'Samoa':'萨摩亚',
                                    'Dominican Rep.':'多米尼加',
                                    'Dominica':'多米尼克',
                                    'Aland':'奥兰',
                                    'Mauritius':'毛里求斯',
                                    'Seychelles':'塞舌尔',
                                    'Comoros':'科摩罗',
                                    'Congo':'刚果',
                                    'Singapore':'新加坡'
                                    
                                  
                                   
                                }

                }]
            };
       var     options = {
                title: {
                    text: '异常IP全国分布图',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: function(params) {

                        var value = (params.value + '').split('.');
                        if(value=='NaN') value=0;
                        return  params.name + ' : ' + value;
                    }
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: ['异常IP全国分布图']
                },
                dataRange: {
                    x: 'left',
                    y: 'bottom',
                    splitList: [
                        {start: 1500},
                        {start: 1000, end: 1500},
                        {start: 300, end: 1000},
                        {start: 200, end: 300},
                        {start: 10, end: 200},
                        {end: 10}
                    ],
                    color: ['#8B0000', '#EE4F4D']
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    x: 'right',
                    y: 'center',
                    feature: {
                        mark: {
                            show: true
                        },
                        dataView: {
                            show: true,
                            readOnly: false
                        },
                        saveAsImage: {
                            show: true
                        }
                    }
                },
                roamController: {
                    show: true,
                    x: 'right',
                    mapTypeControl: {
                        'china': true
                    }
                },
                series: [{
                    name: '异常IP全国分布',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    itemStyle: {
                        normal: {
                            label: {
                                show: true,
                                position: 'inner',

                            }
                        },
                        emphasis: {
                            label: {
                                show: true
                            }
                        }
                    },
                    data:$scope.dataProvince
                }
                ]
            };
            //获取世界操作（访问过的url）性能信息，图表：y轴访问花费时间--，用echarts绘出折线图
            $scope.getCountryMetrics = function () {

                $scope.loading = true;
                var url = '/dataMapAccount/getCountryView';
                var   params = getParams();
                $http.post(url, params).success(function (data) {
                    for(keys in $scope.dataCountry){
                        $scope.dataCountry[keys].value='NaN';
                    }
                    for(key in $scope.dataCountry){
                        var name=$scope.dataCountry[key].name;
                        var value=data.data[name];
                        if(value!=null&&value!='undefined'){
                            $scope.dataCountry[key].value=value;
                        }
                    }
                    option.series[0].data=$scope.dataCountry;
                    $scope.loading = false;
                    myChart.setOption(option,true);
                }).error(function () {

                });
            };


            //获取中国操作（访问过的url）性能信息，图表：y轴访问花费时间--，用echarts绘出折线图
            $scope.getProvinceMetrics = function () {

                $scope.loading = true;
                var url = '/dataMapAccount/getProvinceView';
                var   params = getParams();

                $http.post(url, params).success(function (data) {

                    for(keys in $scope.dataProvince){
                        $scope.dataProvince[keys].value='NaN';
                    }

                    for(key in $scope.dataProvince){
                        var name=$scope.dataProvince[key].name;
                        var value=data.data[name];
                        if(value!=null&&value!='undefined'){
                            $scope.dataProvince[key].value=value;
                        }
                    }
                    myChart.setOption(options);
                    options.series[0].data=$scope.dataProvince;
                    $scope.loading = false;

                }).error(function () {

                });
            };

			
			$scope.load = function() {
               $scope.getCountryMetrics();
                var nowdate = new Date()
                var startStr = $filter("date")(nowdate - 86400000*6, "yyyy-MM-dd 00:00:00");
                drp.startDate._d=new Date(dateStrToSec(startStr)*1000);
                var endStr =$filter("date")(nowdate, "yyyy-MM-dd HH:mm:00");
                drp.endDate._d=nowdate;
                $('input[name="daterange"]').val(startStr+' - '+endStr);
			};


		} ]);



