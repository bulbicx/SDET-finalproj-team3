/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 99.99810724357883, "KoPercent": 0.0018927564211761588};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.5730887892037174, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.5703908682116915, 500, 1500, "Read Genre"], "isController": false}, {"data": [0.5995115995115995, 500, 1500, "Update Playlist"], "isController": false}, {"data": [0.5967160592711254, 500, 1500, "Read Playlist One"], "isController": false}, {"data": [0.6000823723228995, 500, 1500, "Delete Playlist"], "isController": false}, {"data": [0.5769096005606167, 500, 1500, "Read Genre One"], "isController": false}, {"data": [0.587007874015748, 500, 1500, "Read Playlist"], "isController": false}, {"data": [0.5924484235110938, 500, 1500, "Create Playlist"], "isController": false}, {"data": [0.5933005299015897, 500, 1500, "Read User One"], "isController": false}, {"data": [0.5693877551020409, 500, 1500, "Create Genre"], "isController": false}, {"data": [0.5644363876071193, 500, 1500, "Update Artist"], "isController": false}, {"data": [0.5787695590327169, 500, 1500, "Update Genre"], "isController": false}, {"data": [0.5563579277864992, 500, 1500, "Create Artist"], "isController": false}, {"data": [0.58492296404989, 500, 1500, "Create User"], "isController": false}, {"data": [0.5594230769230769, 500, 1500, "Update User"], "isController": false}, {"data": [0.5823104693140794, 500, 1500, "Delete Genre"], "isController": false}, {"data": [0.5685353208036293, 500, 1500, "Read Artist One"], "isController": false}, {"data": [0.4977661950856292, 500, 1500, "Read User"], "isController": false}, {"data": [0.5573299265410412, 500, 1500, "Read Artist"], "isController": false}, {"data": [0.568676716917923, 500, 1500, "Delete Artist"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 52833, 1, 0.0018927564211761588, 1114.5654231257029, 0, 8783, 2214.0, 3674.0, 4076.9500000000007, 5746.0, 363.27319233202235, 7488.7592107202645, 80.2857308154446], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Read Genre", 2891, 0, 0.0, 1112.6987201660327, 1, 8783, 648.0, 2855.6000000000004, 3516.0, 5281.199999999992, 19.948799690866057, 91.01825843908061, 3.6040311941506062], "isController": false}, {"data": ["Update Playlist", 2457, 0, 0.0, 1030.3878713878714, 1, 5901, 561.0, 2702.0000000000027, 3353.1, 5334.780000000001, 17.023251946900203, 68.14129733227905, 4.880065469715656], "isController": false}, {"data": ["Read Playlist One", 2497, 0, 0.0, 1050.2679215058072, 1, 6709, 561.0, 2734.0000000000014, 3438.099999999998, 5595.599999999999, 17.27418004718058, 69.68909194462509, 3.2483196350250085], "isController": false}, {"data": ["Delete Playlist", 2428, 0, 0.0, 1026.3175453047782, 1, 6684, 554.0, 2695.1, 3414.0999999999995, 5158.430000000023, 16.818713936396446, 3.1370843377458213, 3.5566538430899883], "isController": false}, {"data": ["Read Genre One", 2854, 0, 0.0, 1098.655220742817, 0, 5817, 623.0, 2868.5, 3467.25, 5368.299999999997, 19.70722275928739, 6.22804386479768, 3.6491690115315567], "isController": false}, {"data": ["Read Playlist", 2540, 0, 0.0, 1084.31968503937, 1, 5926, 592.0, 2821.8, 3554.95, 5462.880000000005, 17.578584577906348, 5533.5808912805205, 3.2273182623499936], "isController": false}, {"data": ["Create Playlist", 2569, 0, 0.0, 1042.9528999610734, 1, 5801, 572.0, 2774.0, 3436.0, 4570.100000000034, 17.791351560985067, 72.37224504357809, 5.160186927356022], "isController": false}, {"data": ["Read User One", 2642, 0, 0.0, 1062.719530658592, 0, 5827, 573.0, 2760.7000000000003, 3503.0, 5439.950000000006, 18.247746658838967, 5.730614575059572, 3.360545917222088], "isController": false}, {"data": ["Create Genre", 2940, 0, 0.0, 1116.9095238095222, 0, 6668, 642.5, 2888.4000000000015, 3511.0, 5529.540000000015, 20.294196826098062, 6.4534057493321555, 5.232097619228407], "isController": false}, {"data": ["Update Artist", 3034, 0, 0.0, 1123.1977587343442, 0, 6820, 660.0, 2873.5, 3476.5, 5412.100000000001, 20.93525527349006, 6.166825443511382, 4.858371988918253], "isController": false}, {"data": ["Update Genre", 2812, 0, 0.0, 1101.5213371265993, 1, 6454, 630.5, 2864.7000000000003, 3530.7, 5560.999999999989, 19.413856191100834, 6.135222134005316, 5.073526873554489], "isController": false}, {"data": ["Create Artist", 3185, 0, 0.0, 1163.5425431711162, 1, 6673, 683.0, 2920.8, 3514.7, 5583.28, 21.943724852560216, 6.464256509914292, 5.014483999510831], "isController": false}, {"data": ["Create User", 2726, 1, 0.036683785766691124, 1074.792369772561, 6, 6674, 594.5, 2808.0, 3421.1000000000013, 5227.790000000001, 18.828567481696368, 7.516178911538196, 5.2219855125017265], "isController": false}, {"data": ["Update User", 2600, 0, 0.0, 1161.1088461538463, 110, 6834, 683.0, 2854.8, 3503.3499999999976, 4786.889999999998, 17.943780754604997, 5.670083749145945, 5.039247706991864], "isController": false}, {"data": ["Delete Genre", 2770, 0, 0.0, 1086.0916967509015, 1, 5899, 605.5, 2835.2000000000007, 3472.499999999998, 5578.349999999999, 19.136706552076713, 3.5694442885221216, 3.9918243689031976], "isController": false}, {"data": ["Read Artist One", 3086, 0, 0.0, 1130.1636422553477, 0, 6902, 651.5, 2901.2000000000007, 3495.0, 5355.370000000005, 21.288043928147676, 6.229294428271847, 3.9632819398264396], "isController": false}, {"data": ["Read User", 2686, 0, 0.0, 1396.0446760982875, 7, 7626, 892.0, 3425.3, 4153.25, 6002.100000000008, 18.515454821187305, 1556.9399525675026, 3.3269957881820935], "isController": false}, {"data": ["Read Artist", 3131, 0, 0.0, 1149.4091344618323, 1, 6735, 676.0, 2918.8, 3555.199999999999, 5373.68, 21.57599145505289, 71.86148701030217, 3.9190765728904657], "isController": false}, {"data": ["Delete Artist", 2985, 0, 0.0, 1124.8904522613052, 1, 6407, 648.0, 2861.8, 3514.3999999999996, 5379.279999999993, 20.646865964834618, 3.8511244133627067, 4.327578080688782], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["500", 1, 100.0, 0.0018927564211761588], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 52833, 1, "500", 1, null, null, null, null, null, null, null, null], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Create User", 2726, 1, "500", 1, null, null, null, null, null, null, null, null], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
