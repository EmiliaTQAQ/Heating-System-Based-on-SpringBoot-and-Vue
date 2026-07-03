  <template>
    <div style="height: 680px">
      <div style="width: 100%; margin-top: 20px;margin-left:20px; float: left">
        <div id="bie_cell" style="width: 100%; height: 680px"></div>
      </div>
    </div>
  </template>
  <script>
  import request from "@/utils/request";
  import * as echarts from "echarts";

  export default {
    data() {
      return {
        user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
        sum: '',
        times: '',
        tableData: [],
        dialogFormVisible: false,
        form: {},
        total: 0,
        obj: [],
        params: {
          pageNum: 1,
          pageSize: 5,
        },
        typeObjs: []
      }
    },
    mounted() {
      this.initEcharts_cell()
      // this.findBySearch();
      // this.findByHeating()
    },
    methods: {
      initEcharts_cell() {
        request.get("room/echarts/bie/" + this.user.account).then(res => {
          if (res.code === '0') {
            this.initBie_cell(res.data)
            console.log(res.data)
          } else {
            this.$message.error(res.msg)
          }
        })
      },
      initBie_cell(data) {
        var chartDom = document.getElementById('bie_cell');
        var myChart = echarts.init(chartDom);
        var option;
        // let xAxisData = [];
        // if (data.length > 0) {
        //   // 遍历 data 数组，提取所需的数据添加到 xAxisData 中
        //   for (let i = 0; i < data.length; i++) {
        //     xAxisData.push(data[i].name);
        //   }
        // }
        option = {
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '5%',
            left: 'left'
          },
          series: [
            {
              name: '负责区域',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              padAngle: 5,
              itemStyle: {
                borderRadius: 10
              },
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: 40,
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: data

            }
          ]
        };

        option && myChart.setOption(option);
      }
    }
  }


      // initBie_cell(data) {
      //   var chartDom = document.getElementById('bie_cell');
      //   var myChart = echarts.init(chartDom);
      //   var option;
      //   let xAxisData = [];
      //   if (data.length > 0) {
      //     // 遍历 data 数组，提取所需的数据添加到 xAxisData 中
      //     for (let i = 0; i < data.length; i++) {
      //       xAxisData.push(data[i].name);
      //     }
      //   }
      //   option = {
      //     tooltip: {
      //       trigger: 'axis',
      //       axisPointer: {
      //         type: 'shadow'
      //       }
      //     },
      //     grid: {
      //       left: '3%',
      //       right: '4%',
      //       bottom: '3%',
      //       containLabel: true
      //     },
      //     xAxis: [
      //       {
      //         type: 'category',
      //         data:  xAxisData,
      //         axisTick: {
      //           alignWithLabel: true
      //         }
      //       }
      //     ],
      //     yAxis: [
      //       {
      //         type: 'value'
      //       }
      //     ],
      //     title: {
      //       text: '负责区域（住状图）',
      //       subtext: '统计维度：户',
      //       left: 'center'
      //     },
      //     series: [
      //       {
      //         name: '房子数',
      //         type: 'bar',
      //         barWidth: '60%',
      //         data: data
      //       }
      //     ]
      //   };
      //
      //   option && myChart.setOption(option);
      //
      // },


  </script>