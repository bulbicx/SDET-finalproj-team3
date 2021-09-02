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

    var data = {"OkPercent": 100.0, "KoPercent": 0.0};
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
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [1.0, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [1.0, 500, 1500, "Read Genre"], "isController": false}, {"data": [1.0, 500, 1500, "Update Playlist"], "isController": false}, {"data": [1.0, 500, 1500, "Read Playlist One"], "isController": false}, {"data": [1.0, 500, 1500, "Delete Playlist"], "isController": false}, {"data": [1.0, 500, 1500, "Read Genre One"], "isController": false}, {"data": [1.0, 500, 1500, "Read Playlist"], "isController": false}, {"data": [1.0, 500, 1500, "Create Playlist"], "isController": false}, {"data": [1.0, 500, 1500, "Read User One"], "isController": false}, {"data": [1.0, 500, 1500, "Create Genre"], "isController": false}, {"data": [1.0, 500, 1500, "Update Artist"], "isController": false}, {"data": [1.0, 500, 1500, "Update Genre"], "isController": false}, {"data": [1.0, 500, 1500, "Create Artist"], "isController": false}, {"data": [1.0, 500, 1500, "Create User"], "isController": false}, {"data": [1.0, 500, 1500, "Delete Genre"], "isController": false}, {"data": [1.0, 500, 1500, "Read Artist One"], "isController": false}, {"data": [1.0, 500, 1500, "Read User"], "isController": false}, {"data": [1.0, 500, 1500, "Read Artist"], "isController": false}, {"data": [1.0, 500, 1500, "Delete Artist"], "isController": false}]}, function(index, item){
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
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 11766, 0, 0.0, 18.43481217066115, 0, 273, 3.0, 64.0, 88.0, 129.0, 36.9671016045469, 163.0388495163895, 7.987649380621962], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Read Genre", 678, 0, 0.0, 43.874631268436545, 2, 155, 35.0, 96.10000000000002, 106.0, 123.21000000000004, 2.380434095680811, 35.025617425093564, 0.43005889423920907], "isController": false}, {"data": ["Update Playlist", 500, 0, 0.0, 8.873999999999997, 1, 75, 3.0, 30.0, 52.94999999999999, 63.99000000000001, 2.785049852392358, 1.0574050994262798, 0.7963066757644962], "isController": false}, {"data": ["Read Playlist One", 501, 0, 0.0, 8.03592814371258, 0, 94, 2.0, 29.0, 43.799999999999955, 79.90000000000009, 2.5934630237397633, 0.9846660608997919, 0.48572835027798195], "isController": false}, {"data": ["Delete Playlist", 491, 0, 0.0, 8.032586558044816, 1, 86, 3.0, 27.0, 45.0, 76.23999999999995, 2.843805022704105, 0.5304362884145353, 0.5992542613636364], "isController": false}, {"data": ["Read Genre One", 642, 0, 0.0, 5.943925233644864, 0, 89, 2.0, 16.0, 36.0, 55.0, 2.3162679943716853, 0.7302391695529818, 0.42713378747699965], "isController": false}, {"data": ["Read Playlist", 504, 0, 0.0, 58.888888888888864, 2, 180, 56.0, 114.0, 121.75, 141.89999999999998, 2.3788396548794535, 67.12048819902014, 0.4367400928880246], "isController": false}, {"data": ["Create Playlist", 508, 0, 0.0, 8.74212598425197, 1, 83, 3.0, 31.200000000000045, 46.09999999999991, 70.54999999999984, 2.308283010037396, 0.880906171476347, 0.6694922402159245], "isController": false}, {"data": ["Read User One", 510, 0, 0.0, 7.488235294117645, 0, 90, 2.0, 26.0, 43.89999999999998, 77.77999999999997, 2.2315373104287177, 0.6990731599662207, 0.4092348178890532], "isController": false}, {"data": ["Create Genre", 722, 0, 0.0, 5.950138504155122, 0, 83, 2.0, 14.0, 35.700000000000045, 69.76999999999998, 2.5148557615275835, 0.7978046280138212, 0.6483612510188301], "isController": false}, {"data": ["Update Artist", 873, 0, 0.0, 5.455899198167246, 1, 81, 3.0, 6.0, 26.299999999999955, 69.03999999999996, 2.874149771845843, 0.8444956214896194, 0.6648612607492542], "isController": false}, {"data": ["Update Genre", 578, 0, 0.0, 6.73529411764706, 1, 87, 3.0, 16.200000000000045, 35.0, 70.21000000000004, 2.121731597282128, 0.6688699783238321, 0.5528377815974657], "isController": false}, {"data": ["Create Artist", 987, 0, 0.0, 7.6322188449848, 1, 194, 2.0, 20.0, 44.59999999999991, 77.12, 3.140631562869907, 0.9228383248930849, 0.7176833844839438], "isController": false}, {"data": ["Create User", 519, 0, 0.0, 11.329479768786104, 4, 96, 7.0, 22.0, 46.0, 75.79999999999995, 2.06332294383309, 0.774922470093744, 0.5722497227037084], "isController": false}, {"data": ["Delete Genre", 538, 0, 0.0, 6.758364312267656, 1, 89, 3.0, 20.0, 40.049999999999955, 68.83000000000004, 2.121509663121616, 0.3957112750549108, 0.44087468650554235], "isController": false}, {"data": ["Read Artist One", 948, 0, 0.0, 8.206751054852319, 0, 90, 2.0, 29.0, 50.549999999999955, 77.50999999999999, 3.032425844712927, 0.8851072864586833, 0.5623197697851392], "isController": false}, {"data": ["Read User", 514, 0, 0.0, 49.326848249027265, 1, 142, 44.5, 98.0, 112.0, 129.85000000000002, 2.125433667034689, 56.6052990984481, 0.3819138620452957], "isController": false}, {"data": ["Read Artist", 972, 0, 0.0, 66.60699588477374, 1, 273, 59.0, 129.0, 156.0, 203.26999999999998, 3.110569214965294, 36.17352408971048, 0.5650057363120554], "isController": false}, {"data": ["Delete Artist", 781, 0, 0.0, 6.55185659411012, 1, 91, 3.0, 14.600000000000136, 33.89999999999998, 77.17999999999995, 2.6714828611205177, 0.4982941664785341, 0.55793741042323], "isController": false}]}, function(index, item){
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
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": []}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 11766, 0, null, null, null, null, null, null, null, null, null, null], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
