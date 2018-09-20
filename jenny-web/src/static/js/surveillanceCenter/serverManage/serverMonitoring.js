$(function() {
	//加载服务菜单
	request('get', '', '/serverMonitoring/menuApplications', 'health', function(result) {
		var applications = result.data;
		jQuery.each(applications, function(index, application) {
			var applictionStr = '';
			applictionStr += '<div class="collapsible-menu">';
			applictionStr += '	<span class="inbox-leftbar-category clearfix">';
			applictionStr += '		<a href="javascript:;" data-toggle="collapse" data-target="#'+application.name+'" class="category-heading">'+application.name+'</a>';
			applictionStr += '	</span>';
			applictionStr += '	<div class="collapse in" id="'+application.name+'"></div>';
			applictionStr += '</div>';
			$('#applicationMenu').append(applictionStr);
			jQuery.each(application.instances, function(index, instance) {
				var instanceStr = '<a href="#" class="inbox-menu-item" id="'+instance.instanceId+'"><i class="text-info fa fa-circle-o mr5"></i>'+instance.serverUrl+'</a>';
				var $this = $('.collapsible-menu .collapse[id="'+application.name+'"]');
				$this.append(instanceStr);
			});
		});
		
		$('.inbox-menu-item').click(function (){
			instanceId = $(this).attr('id');
			usedHeapData = [];
			committedHeapData = [];
			if (!initSetTime) {
				updatePlot();
			}
		});
	});
});

var instanceId,
	updateInterval = 1000,
	initSetTime = false,
	totalPoints = 300,
	
	cpuUsageData = [],
	cpuProcessUsageData = [],
	plotCpuObj,
	
	committedHeapData = [],
	usedHeapData = [],
	plotHeapObj,
	
	committedNonheapData = [],
	usedNonheapData = [],
	usedMetaspaceData = [],
	plotNonheapObj,
	
	peakThreadsData = [],
	liveThreadsData = [],
	daemonThreadsData = [],
	plotThreadsObj;
//加载监控数据
function loadPlot() {
	requestSynchronizedNonloader('get', '', '/serverMonitoring/instance/'+instanceId, 'health', false, false, function(result) {
		//加载CPU数据
		var plotCpuUsageData = {
				label: '系统CPU使用率',
				data: getPlotData(cpuUsageData, result.data.cpuUsage)
			},
			plotCpuProcessUsageData = {
				label: '进程CPU使用率',
				data: getPlotData(cpuProcessUsageData, result.data.cpuProcessUsage)
			};
		cpuUsageData = getData(plotCpuUsageData.data);
		cpuProcessUsageData = getData(plotCpuProcessUsageData.data);
		var plotCpuData = [plotCpuUsageData, plotCpuProcessUsageData];
		
		//加载JVM堆内存数据
		var plotCommittedHeapData = {
				label: '可用堆内存',
				data: getPlotData(committedHeapData, result.data.committedHeap)
			},
			plotUsedHeapData = {
				label: '已用堆内存',
				data: getPlotData(usedHeapData, result.data.usedHeap)
			};
		usedHeapData = getData(plotUsedHeapData.data);
		committedHeapData = getData(plotCommittedHeapData.data);
		var plotHeapData = [plotCommittedHeapData, plotUsedHeapData];
		
		//加载JVM非堆内存数据
		var plotCommittedNonheapData = {
				label: '可用非堆内存',
				data: getPlotData(committedNonheapData, result.data.committedNonheap)
			},
			plotUsedNonheapData = {
				label: '已用非堆内存',
				data: getPlotData(usedNonheapData, result.data.usedNonheap)
			},
			plotUsedMetaspaceData = {
				label: '已用元空间内存',
				data: getPlotData(usedMetaspaceData, result.data.usedMetaspace)
			};
		usedNonheapData = getData(plotUsedNonheapData.data);
		committedNonheapData = getData(plotCommittedNonheapData.data);
		usedMetaspaceData = getData(plotUsedMetaspaceData.data);
		var plotNonheapData = [plotCommittedNonheapData, plotUsedNonheapData, plotUsedMetaspaceData];
		
		//加载JVM线程数据
		var plotPeakThreadsData = {
				label: 'JVM峰值线程数',
				data: getPlotData(peakThreadsData, result.data.peakThreads)
			},
			plotLiveThreadsData = {
				label: 'JVM当前活跃线程数',
				data: getPlotData(liveThreadsData, result.data.liveThreads)
			},
			plotDaemonThreadsData = {
				label: 'JVM守护线程数',
				data: getPlotData(daemonThreadsData, result.data.daemonThreads)
			};
		peakThreadsData = getData(plotPeakThreadsData.data);
		liveThreadsData = getData(plotLiveThreadsData.data);
		daemonThreadsData = getData(plotDaemonThreadsData.data);
		var plotThreadsData = [plotPeakThreadsData, plotLiveThreadsData, plotDaemonThreadsData];
		if (!initSetTime) {
			initSetTime = true;
			//加载CPU数据图表
			var cpu_y_data = [[0,'0']], cpu_y = 10;
			for (var i = 1; i <= 10; i++) {
				cpu_y_data.push([cpu_y*i,cpu_y*i+'%'])
			}
			plotCpuObj = plotCpu(cpu_y_data, plotCpuData);
			
			//加载JVM堆内存数据图表
			var heap_y_data = [[0,'0']], heap_y = parseInt(result.data.maxHeap / 10);
			for (var i = 1; i <= 10; i++) {
				heap_y_data.push([heap_y*i,heap_y*i+'MB'])
			}
			plotHeapObj = plotHeap(heap_y_data, plotHeapData);
			
			//加载JVM非堆内存数据图表
			var nonheap_y_data = [[0,'0']], nonheap_y = parseInt(result.data.maxNonheap / 10);
			for (var i = 1; i <= 10; i++) {
				nonheap_y_data.push([nonheap_y*i,nonheap_y*i+'MB'])
			}
			plotNonheapObj = plotNonheap(nonheap_y_data, plotNonheapData);
			
			//加载JVM线程数据图表
			var threads_y_data = [[0,'0']], threads_y = parseInt(result.data.peakThreads / 5);
			for (var i = 1; i <= 10; i++) {
				threads_y_data.push([threads_y*i,threads_y*i])
			}
			plotThreadsObj = plotThreads(threads_y_data, plotThreadsData);
		} else {
			//刷新CPU数据图表
			plotCpuObj.setData(plotCpuData);
    		plotCpuObj.draw();
    		
			//刷新JVM堆内存数据图表
			plotHeapObj.setData(plotHeapData);
    		plotHeapObj.draw();
    		
			//刷新JVM非堆内存数据图表
			plotNonheapObj.setData(plotNonheapData);
    		plotNonheapObj.draw();
    		
			//刷新JVM线程数据图表
			plotThreadsObj.setData(plotThreadsData);
    		plotThreadsObj.draw();
		}
	});
}
//加载CPU数据图表
function plotCpu(y_data, plotCpuData) {
	return $.plot('#plotCpu', plotCpuData, {
        series: {
            lines: {
	            show: true,
	            lineWidth: 0.75,
	            fill: 0.15,
	            fillColor: { colors: [ { opacity: 0.01 }, { opacity: 0.3 } ] }
            },
            shadowSize: 0
        },
        grid: {
            labelMargin: 10,
            hoverable: true,
            clickable: true,
            borderWidth: 1,
            borderColor: 'rgba(0, 0, 0, 0.06)'
        },
        yaxis: {
            ticks: y_data,
            tickColor: 'rgba(0, 0, 0, 0.06)',
            font: {color: 'rgba(0, 0, 0, 0.4)'}
        },
        xaxis: {
            show: true,
            ticks: [[0,'5分钟前'],[59,'4分钟前'],[119,'3分钟前'],[179,'2分钟前'],[239,'1分钟前'],[299,'当前']]
        },
//      colors: ["#95a5a6"],
        tooltip: true,
        tooltipOpts: {
            content: '%s: %y%'
        }
   });
}
//加载JVM堆内存数据图表
function plotHeap(y_data, plotHeapData) {
	return $.plot('#plotHeap', plotHeapData, {
        series: {
            lines: {
	            show: true,
	            lineWidth: 0.75,
	            fill: 0.15,
	            fillColor: { colors: [ { opacity: 0.01 }, { opacity: 0.3 } ] }
            },
            shadowSize: 0
        },
        grid: {
            labelMargin: 10,
            hoverable: true,
            clickable: true,
            borderWidth: 1,
            borderColor: 'rgba(0, 0, 0, 0.06)'
        },
        yaxis: {
            ticks: y_data,
            tickColor: 'rgba(0, 0, 0, 0.06)',
            font: {color: 'rgba(0, 0, 0, 0.4)'}
        },
        xaxis: {
            show: true,
            ticks: [[0,'5分钟前'],[59,'4分钟前'],[119,'3分钟前'],[179,'2分钟前'],[239,'1分钟前'],[299,'当前']]
        },
//      colors: ["#95a5a6"],
        tooltip: true,
        tooltipOpts: {
            content: '%s: %yMB'
        }
   });
}
//加载JVM非堆内存数据图表
function plotNonheap(y_data, plotNonheapData) {
	return $.plot('#plotNonheap', plotNonheapData, {
        series: {
            lines: {
	            show: true,
	            lineWidth: 0.75,
	            fill: 0.15,
	            fillColor: { colors: [ { opacity: 0.01 }, { opacity: 0.3 } ] }
            },
            shadowSize: 0
        },
        grid: {
            labelMargin: 10,
            hoverable: true,
            clickable: true,
            borderWidth: 1,
            borderColor: 'rgba(0, 0, 0, 0.06)'
        },
        yaxis: {
            ticks: y_data,
            tickColor: 'rgba(0, 0, 0, 0.06)',
            font: {color: 'rgba(0, 0, 0, 0.4)'}
        },
        xaxis: {
            show: true,
            ticks: [[0,'5分钟前'],[59,'4分钟前'],[119,'3分钟前'],[179,'2分钟前'],[239,'1分钟前'],[299,'当前']]
        },
        tooltip: true,
        tooltipOpts: {
            content: '%s: %yMB'
        }
   });
}
//加载JVM线程数据图表
function plotThreads(y_data, plotThreadsData) {
	return $.plot('#plotThreads', plotThreadsData, {
        series: {
            lines: {
	            show: true,
	            lineWidth: 0.75,
	            fill: 0.15,
	            fillColor: { colors: [ { opacity: 0.01 }, { opacity: 0.3 } ] }
            },
            shadowSize: 0
        },
        grid: {
            labelMargin: 10,
            hoverable: true,
            clickable: true,
            borderWidth: 1,
            borderColor: 'rgba(0, 0, 0, 0.06)'
        },
        yaxis: {
            ticks: y_data,
            tickColor: 'rgba(0, 0, 0, 0.06)',
            font: {color: 'rgba(0, 0, 0, 0.4)'}
        },
        xaxis: {
            show: true,
            ticks: [[0,'5分钟前'],[59,'4分钟前'],[119,'3分钟前'],[179,'2分钟前'],[239,'1分钟前'],[299,'当前']]
        },
        tooltip: true,
        tooltipOpts: {
            content: '%s: %y'
        }
   });
}
//构建图表数据
function getPlotData(dxta, num) {
    if (dxta.length > 0)
        dxta = dxta.slice(1);
    while (dxta.length < totalPoints) {
        var value = 0;
		if (dxta.length == totalPoints - 1 && num) {
			value = num;
		}
        dxta.push(value);
    }
    var res = [];
    for (var i = 0; i < dxta.length; ++i) {
        res.push([i, dxta[i]])
    }
    return res;
}
function getData(dxta) {
	var res = [];
	for (var i = 0; i < dxta.length; ++i) {
        res.push(dxta[i][1])
    }
    return res;
}
//时间段触发刷新
function updatePlot() {
	loadPlot();
    setTimeout(updatePlot, updateInterval);
}



