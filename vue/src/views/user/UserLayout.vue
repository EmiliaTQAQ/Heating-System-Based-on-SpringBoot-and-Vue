<template>
  <div style="height: 700px">
  <el-container style="background-color: darkslategray;">
    <el-aside style="overflow: hidden; min-height: 700px; width: 212px; background-color: darkslategray">
      <div style="height: 60px">
        <img :src="user.img" alt="" style="width: 209px; height:200px; border-radius: 50%; ">
      </div>
      <div style="margin-top: 160px;color: white">
        <span>{{ user.name }}</span>
      </div>
      <el-menu :default-active="$route.path" router background-color="darkslategray" text-color="white" active-text="#409EFF" style="margin-top: 20px">
        <el-menu-item index="/UserLayout/home">回到首页</el-menu-item>
        <el-menu-item index="/UserLayout/Maintenance">维修记录</el-menu-item>
        <el-menu-item index="/UserLayout/F_history">意见箱</el-menu-item>
        <el-menu-item @click="back()">退出</el-menu-item>
      </el-menu>
    </el-aside>
    <el-main style="margin-right: 100px;">
      <div class="rounded-rectangle" >
        <scroll-announcement :noticeList="announcements"></scroll-announcement>
        <router-view  @transmit="getMessage" style="height: 700px"></router-view>
      </div>
    </el-main>
  </el-container>
  </div>
</template>
<script>
import request from "@/utils/request";
import ScrollAnnouncement from "@/views/user/ScrollAnnouncement.vue";

export default {
  components: {ScrollAnnouncement},

  data() {
    return {
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      form: {
      },
      times: '',
      x: '',
      dialogFormVisible: false,
      announcements: [

      ]
    }
  },

  created() {
    this.tz();
    this.first();
    this.getMessage();
    this.getTimes();
  },
  mounted() {
    this.startMove()

  },
  beforeDestroy() {
    if (this.times) {
      clearInterval(this.getTimesInterval);
    }
  },
  methods: {
    startMove () {
      // console.log(this.textArr[0].tag)
      let state = "已公布"
      request.get("/notice/findState/" + state).then(res => {
        if (res.code === '0'){
          console.log(res.data)
          this.announcements = res.data
        }

      })

    },
    getTimes() {
      setInterval(this.getTimesInterval, 1000);
    },
    getTimesInterval:function() {
      let _this = this;
      let year = new Date().getFullYear(); //获取当前时间的年份
      let month = new Date().getMonth() + 1; //获取当前时间的月份
      let day = new Date().getDate(); //获取当前时间的天数
      let hours = new Date().getHours(); //获取当前时间的小时
      let minutes = new Date().getMinutes(); //获取当前时间的分数
      let seconds = new Date().getSeconds(); //获取当前时间的秒数
      //当小于 10 的是时候，在前面加 0
      if (hours < 10) {
        hours = "0" + hours;
      }
      if (minutes < 10) {
        minutes = "0" + minutes;
      }
      if (seconds < 10) {
        seconds = "0" + seconds;
      }
      //拼接格式化当前时间
      _this.times = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
    },
    first() {
      console.log(this.user.name)
      if (this.user.role === "user") {
      } else {
        this.$message.error('用户身份有误，请重新登录')
        this.$router.push("/UserLogin")
      }
    },
    tz() {

      request.get("/user/tz/" + this.user.account).then(res => {
        if (res.data != null) {
          this.$notify({
            message: '还没有缴费',
            type: 'warning'
          });
        }

      })
    },
    getMessage(data) {
      if (data === undefined) {

      } else {
        this.user.img = data
      }
    },
    // jf() {
    //   console.log(this.user.account)
    //   request.get("pay_the_fees/find/" + this.user.account).then(res => {
    //     if (res.code === '0') {
    //       console.log(res.data)
    //      if (res.data) {
    //        this.$message.error("已交款")
    //      } else {
    //        this.form.account = this.user.account
    //        this.form.paymentCycle = "1年"
    //        this.form.name = this.user.name
    //        this.form.datetime = this.times
    //        this.form.address = this.user.address
    //        this.form.living_space = this.user.living_space
    //        this.form.paymentAmount = this.user.living_space * 30
    //        if ( this.form.paymentAmount > this.user.balance) {
    //          this.$message.error("余额不足")
    //        } else {
    //          this.x = this.user.balance - this.form.paymentAmount
    //          request.get("user/upBalance/" + this.user.account + '/' + this.x).then(res => {
    //            if (res.code === '0') {
    //              console.log(res.data)
    //              this.x = res.data
    //            } else {
    //              this.$message.error(res.msg)
    //            }
    //          })
    //          console.log(this.form)
    //          request.post("/pay_the_fees", this.form).then(res => {
    //            if (res.code === '0') {
    //              this.$message({
    //                message: '操作成功',
    //                type: 'success'
    //              });
    //              this.dialogFormVisible = false;
    //            } else {
    //              this.$message({
    //                message: res.msg,
    //                type: 'error'
    //              });
    //              console.log(res.msg)
    //            }
    //          })
    //        }
    //      }
    //     } else {
    //       this.$message.error(res.msg)
    //     }
    //   })
    //
    // },
    // maintenance() {
    //   this.form = {};
    //   this.dialogFormVisible = true;
    // },
    // submit() {
    //   request.get("/room/findAccount/" + this.user.cell).then(res => {
    //     if (res.code === '0') {
    //       // console.log(res.data)
    //      this.form.heating_account = res.data
    //       console.log(this.form.heating_account + "1")
    //       console.log(this.form.heating_account )
    //       request.get("/heating_engineer/findName/" +  this.form.heating_account ).then(res => {
    //         if (res.code === '0') {
    //           // console.log(res.data)
    //           this.form.heating_name = res.data
    //           this.form.account = this.user.account;
    //           this.form.name = this.user.name;
    //           this.form.address = this.user.address
    //           this.form.datetime = this.times
    //           this.form.state = '待解决'
    //           // console.log(this.form)
    //           request.post("/maintain", this.form).then(res => {
    //             if (res.code === '0') {
    //               this.$message({
    //                 message: '操作成功',
    //                 type: 'success'
    //               });
    //               this.dialogFormVisible = false;
    //             } else {
    //               this.$message({
    //                 message: res.msg,
    //                 type: 'error'
    //               });
    //               console.log(res.msg)
    //             }
    //           })
    //         } else {
    //           this.$message({
    //             message: res.msg,
    //             type: 'error'
    //           });
    //           console.log(res.msg)
    //         }
    //       })
    //
    //     } else {
    //       this.$message({
    //         message: res.msg,
    //         type: 'error'
    //       });
    //       console.log(res.msg)
    //     }
    //   })
    //
    // },
    back() {
      localStorage.removeItem("user");
      this.$router.push('/UserLogin')
    }
  }
}

</script>
<style scoped>
.el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        //line-height: 500px;
      }

.el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        //line-height: 300px;
      }

</style>