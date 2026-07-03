<template>
  <div>
    <el-row>
      <el-col :span="12">
        <el-row>
          <el-col :span="12">
            <el-button type="success" style="width: 100%; height: 330px" @click="jf()">
              <font size="90">缴费</font>
            </el-button>
          </el-col>
          <el-col :span="12">
            <el-button type="success" style="width: 100%; height: 330px" @click="prepay()">
              <font size="90">预交费</font>
            </el-button>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="12">
        <el-button type="primary" style="width: 100%; height: 330px" @click="maintenance()">
          <font size="90">维修</font>
        </el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-button type="info" style="width: 100%; height: 330px" @click="message()">
          <font size="90">个人中心</font>
        </el-button>
      </el-col>
      <el-col :span="12">
        <el-button type="warning" style="width: 100%; height: 330px" @click="feedback()">
          <font size="90">提意见</font>
        </el-button>
      </el-col>
    </el-row>
    <div>
      <el-dialog title="申请维修" :visible.sync="dialogFormVisible" width="40%">
        <el-form :model="form">
          <el-form-item label="问题描述" label-width="15%">
            <el-input v-model="form.description" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submit()">提 交</el-button>
          <el-button @click="dialogFormVisible = false">取 消</el-button>
        </div>
      </el-dialog>
    </div>
    <div>
      <el-dialog title="请填写意见信息" :visible.sync="dialogFormVisible1" width="40%">
        <el-form :model="form">
          <el-form-item label="意见内容" label-width="15%">
            <el-input v-model="form.content" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit1()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div>
      <el-dialog title="缴费信息" :visible.sync="dialogFormVisible2" width="40%">
        <el-form :model="from3">
          <el-form-item label="姓名" label-width="15%">
            <el-input v-model="from3.name" autocomplete="off" style="width: 90%" disabled></el-input>
          </el-form-item>
          <el-form-item label="缴费时间" label-width="15%">
            <el-input v-model="from3.time" autocomplete="off" style="width: 90%" disabled></el-input>
          </el-form-item>
          <el-form-item label="缴费金额" label-width="15%">
            <el-input v-model="from3.je" autocomplete="off" style="width: 90%" disabled></el-input>
          </el-form-item>
          <el-form-item label="余额" label-width="15%">
            <el-input v-model="from3.balance" autocomplete="off" style="width: 90%" disabled></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit2()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div>
      <el-dialog title="预缴费信息" :visible.sync="dialogFormVisible3" width="40%">
        <el-form :model="from4">
          <el-form-item label="姓名" label-width="15%">
            <el-input v-model="from4.name" autocomplete="off" style="width: 90%" disabled></el-input>
          </el-form-item>
          <el-form-item label="缴费时间" label-width="15%">
            <el-input v-model="from4.time" autocomplete="off" style="width: 90%" disabled></el-input>
          </el-form-item>
          <el-form-item label="缴费金额" label-width="15%">
            <el-input v-model="from4.je" autocomplete="off" style="width: 90%" disabled></el-input>
          </el-form-item>
          <el-form-item label="余额" label-width="15%">
            <el-input v-model="from4.balance" autocomplete="off" style="width: 90%" disabled></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit3()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import request from "@/utils/request";

export default {
  data() {
    return{
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      form: {
      },
      times: '',
      x: '',
      dialogFormVisible: false,
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      dialogFormVisible3: false,
      from1: {
      },
      from2: {},
      from3: {},
      from4: {},
      currentYear: new Date().getFullYear()


    }
  },
  created() {
    this.getTimes()
  },
  beforeDestroy() {
    if (this.times) {
      clearInterval(this.getTimesInterval);
    }
  },
  methods: {
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
    jf() {

      request.get("/user/findBalance/" + this.user.id).then(res => {
        this.from3.name = this.user.name
        this.from3.time = this.times
        this.from3.je = this.user.living_space * 30
        this.from3.balance = res.data
      })
      this.dialogFormVisible2 = true;
    },
    submit2() {
      console.log(this.user.account)
      request.get("pay_the_fees/find/" + this.user.account).then(res => {
        if (res.code === '0') {
          console.log(res.data)
          if (res.data) {
            this.$message.error("已交款")
          } else {
            this.form.account = this.user.account
            this.form.paymentCycle = "1年"
            this.form.name = this.user.name
            this.form.datetime = this.times
            this.form.address = this.user.address
            this.form.living_space = this.user.living_space
            this.form.paymentAmount = this.user.living_space * 30
            request.get("/user/findBalance/" + this.user.id).then(res => {
              if ( this.form.paymentAmount > res.data) {
                this.$message.error("余额不足")
              } else {
                this.x = res.data  - this.form.paymentAmount
                request.get("user/upBalance/" + this.user.account + '/' + this.x).then(res => {
                  if (res.code === '0') {
                    //修改缴费状态
                    request.get("/room/u/" +  this.user.cell + '/' + this.user.building + '/' + this.user.gate).then(res => {
                      if (res.code === '0') {
                        //缴费记录
                        request.post("/pay_the_fees", this.form).then(res => {
                          if (res.code === '0') {
                            //温度控制
                            this.from2.account = this.user.account
                            this.from2.name = this.user.name
                            this.from2.address = this.user.address
                            this.from2.temperature = "25"
                            request.post("/temperature", this.from2).then(res => {
                              if (res.code === '0') {
                              } else {
                                this.$message({
                                  message: res.msg,
                                  type: 'error'
                                });
                                console.log(res.msg)
                              }
                            })
                            this.$message({
                              message: '操作成功',
                              type: 'success'
                            });
                          } else {
                            this.$message({
                              message: res.msg,
                              type: 'error'
                            });
                            console.log(res.msg)
                          }
                        })
                      } else {
                        this.$message({
                          message: res.msg,
                          type: 'error'
                        });
                        console.log(res.msg)
                      }
                    })
                    console.log(res.data)
                    this.x = res.data
                  } else {
                    this.$message.error(res.msg)
                  }
                })
                console.log(this.form)
              }
            })
          }
        } else {
          this.$message.error(res.msg)
        }
      })
      this.dialogFormVisible2 = false;
    },
    prepay() {
      request.get("/user/findBalance/" + this.user.id).then(res => {
        this.from4.name = this.user.name
        this.from4.time = this.times
        this.from4.je = this.user.living_space * 30
        this.from4.balance = res.data
      })
      this.dialogFormVisible3 = true;
    },
    submit3() {
      const x = this.currentYear + 1
      request.get("advance_fee/find/" + this.user.account).then(res => {
        if (res.code === '0') {
          console.log(res.data)
          if (res.data) {
            this.$message.error('已完成' + x + '预交费')
          } else {
            this.form.account = this.user.account
            this.form.paymentCycle = "1年"
            this.form.name = this.user.name
            this.form.datetime = this.times
            this.form.address = this.user.address
            this.form.living_space = this.user.living_space
            this.form.paymentAmount = this.user.living_space * 30
            console.log(this.form)
            request.get("/user/findBalance/" + this.user.id).then(res => {
              if ( this.form.paymentAmount > res.data) {
                this.$message.error("余额不足")
              } else {
                this.x = res.data - this.form.paymentAmount
                request.get("user/upBalance/" + this.user.account + '/' + this.x).then(res => {
                  if (res.code === '0') {
                    console.log(res.data)
                    this.x = res.data
                  } else {
                    this.$message.error(res.msg)
                  }
                })

                //预缴费记录
                request.post("/advance_fee", this.form).then(res => {
                  if (res.code === '0') {
                    this.$message({
                      message: '已完成' + x + '预交费',
                      type: 'success'
                    });
                  } else {
                    this.$message({
                      message: res.msg,
                      type: 'error'
                    });
                    console.log(res.msg)
                  }
                })
              }
            })
          }
        } else {
          this.$message.error(res.msg)
        }
      })
      this.dialogFormVisible3 = false;
    },
    maintenance() {
      this.form = {};
      this.dialogFormVisible = true;
    },
    submit() {
      request.get("/room/findAccount/" + this.user.cell).then(res => {
        if (res.code === '0') {
          // console.log(res.data)
          this.form.heating_account = res.data
          console.log(this.form.heating_account + "1")
          console.log(this.form.heating_account )
          request.get("/heating_engineer/findName/" +  this.form.heating_account ).then(res => {
            if (res.code === '0') {
              // console.log(res.data)
              this.form.heating_name = res.data
              this.form.account = this.user.account;
              this.form.name = this.user.name;
              this.form.address = this.user.address
              this.form.datetime = this.times
              this.form.state = '待解决'
              // console.log(this.form)
              request.post("/maintain", this.form).then(res => {
                if (res.code === '0') {
                  this.$message({
                    message: '操作成功',
                    type: 'success'
                  });
                  this.dialogFormVisible = false;
                } else {
                  this.$message({
                    message: res.msg,
                    type: 'error'
                  });
                  console.log(res.msg)
                }
              })
            } else {
              this.$message({
                message: res.msg,
                type: 'error'
              });
              console.log(res.msg)
            }
          })

        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
          console.log(res.msg)
        }
      })

    },
    message() {
      this.$router.push('/UserLayout/UserMessage')
    },
    feedback() {
      this.form = {};
      this.dialogFormVisible1 = true;
    },
    submit1() {
      this.form.datetime = this.times
      this.form.name = this.user.name
      this.form.account = this.user.account
      console.log( this.user.name)
      request.post("/feedback",this.form).then(res => {
        if (res.code === '0'){
          this.$message({
            message: '感谢你的意见，已提交至后台',
            type: 'success'
          });
          this.dialogFormVisible1 = false;
        }else {
          this.$message({
            message: res.msg,
            type: 'error'
          });
          console.log(res.msg)
        }
      })
    },
  }
}
</script>
<style>
button {
  z-index: 1;
  color: white;
  background: #40a9ff;
  outline: none;
  border: none;
  padding: 0.5em 1em;
}

button:hover {
  cursor: pointer;
  animation: jelly 0.5s;
}

@keyframes jelly {

  0%,
  100% {
    transform: scale(1, 1);
  }

  33% {
    transform: scale(0.9, 1.1);
  }

  66% {
    transform: scale(1.1, 0.9);
  }
}

@keyframes jelly {

  0%,
  100% {
    transform: scale(1, 1);
  }

  25%,
  75% {
    transform: scale(0.9, 1.1);
  }

  50% {
    transform: scale(1.1, 0.9);
  }
}
</style>