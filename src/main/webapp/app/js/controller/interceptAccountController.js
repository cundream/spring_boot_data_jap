
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


App.controller('interceptAccountController', [ '$scope', '$state', '$http',
		'$stateParams', '$location', '$sce', '$q', '$window','$filter',
		function($scope, $state, $http, $stateParams, $location, $sce, $q, $window,$filter) {

			$scope.lineIds = [];
			$scope.mainTitle = '业务实时监控';
			$scope.loading = false;
			$scope.selectOptions = [{"key":"10","value":"10"},{"key":"30","value":"30"}, {"key":"60","value":"60"}];
			$scope.sumTime = $scope.selectOptions[2];
			$scope.startTime = '';
            $scope.startTime1 = '';
            $scope.startTime2 = '';
		    $scope.endTime = '';
		    $scope.p99StartTime = '';
            var myLineChart = null;
            var myLineChart1 = null;
            var myLineChart2 = null;

			var timer1;
			$scope.isClick = false;  // 一级页面的点击标志
			var isClick2 = false; // 二级页面的点击标志
			var param = '';

			
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





            //获取http操作（访问过的url）性能信息，图表：y轴访问花费时间--，用echarts绘出折线图
            $scope.getHttpMetrics = function () {

                $scope.loading = true;
                var url = '/interceptAccount/getPageView';
                var   params = getParams();
                myLineChart = echarts.init(document.getElementById("main"));
                myLineChart1 = echarts.init(document.getElementById("main1"));
                myLineChart2 = echarts.init(document.getElementById("main2"));
                $http.post(url, params).success(function (data) {

                    data.hitAccountView.tooltip.formatter = function(params,ticket) {
                        var content = '';
                        for(var i=0;i<params.length;i++) {
                            var param = params[i];
                                content += "</li>"+param.seriesName+" : "+param.data+"</li></br>";
                        }
                        return '<div style="text-align:left">'+'<p style="text-align:center">'+params[0].name+'</p>'+content+'</div>'
                    }
                    data.abnormalIpView.tooltip.formatter = function(params,ticket) {
                        var content = '';
                        for(var i=0;i<params.length;i++) {
                            var param = params[i];
                                content += "</li>"+param.seriesName+" : "+param.data+"</li></br>";
                        }
                        return '<div style="text-align:left">'+'<p style="text-align:center">'+params[0].name+'</p>'+content+'</div>'
                    }
                    data.loginCaptchaIpView.tooltip.formatter = function(params,ticket) {
                        var content = '';
                        for(var i=0;i<params.length;i++) {
                            var param = params[i];
                                content += "</li>"+param.seriesName+" : "+param.data+"</li></br>";
                        }
                        return '<div style="text-align:left">'+'<p style="text-align:center">'+params[0].name+'</p>'+content+'</div>'
                    }
                    $scope.timeTitle = data.hitAccountView.title.text;
                    $scope.timeTitle1 = data.abnormalIpView.title.text;
                    $scope.timeTitle2 = data.loginCaptchaIpView.title.text;
                    myLineChart.setOption(data.hitAccountView, true);
                    myLineChart1.setOption(data.abnormalIpView, true);
                    myLineChart2.setOption(data.loginCaptchaIpView, true);
                    $scope.loading = false;
                }).error(function () {

                });
            };
			
			$scope.load = function() {
                $scope.getHttpMetrics();
                var nowdate = new Date()
                var startStr = $filter("date")(nowdate - 86400000*6, "yyyy-MM-dd 00:00:00");
                drp.startDate._d=new Date(dateStrToSec(startStr)*1000);
                var endStr =$filter("date")(nowdate, "yyyy-MM-dd HH:mm:00");
                drp.endDate._d=nowdate;
                $('input[name="daterange"]').val(startStr+' - '+endStr);

			};


		} ]);



