<template>
  <div>
    <div class="background">
      <div class="background">
        <img :src="imgSrc" width="100%" height="100%" alt="" />
      </div>
      <div class="login_container">
        <div class="login_box">
          <div style="font-size: 30px; line-height: 90px; text-align: center; color: #000000">欢迎登录</div>
          <div style="text-align: center">
            <el-form :model="form" class="login_form">
              <el-form-item>
                <el-input v-model="form.account" prefix-icon="el-icon-user" style="width: 80%"  class="in" placeholder="请输入账号"></el-input>
              </el-form-item>
              <el-form-item>
                <el-input v-model="form.password" show-password prefix-icon="el-icon-lock" style="width: 80%" placeholder="请输入密码"></el-input>
              </el-form-item>
              <el-form-item>
                <div style="display: flex; justify-content: center">
                  <el-input v-model="form.verCode" prefix-icon="el-icon-user" style="width: 53%; margin-right: 10px" placeholder="请输入验证码"></el-input>
                  <img :src="captchaUrl" @click="clickImg()" width="140px" height="33px"/>
                </div>
              </el-form-item>
              <el-form-item class="btns">
                <el-button type="primary" @click="login()">登录</el-button>
                <el-button type="info" @click="reg()" style="margin-left: 150px">注册</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>

    </div>
    <div>
      <el-dialog title="填写信息" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form1">
          <el-form-item label="账号" label-width="15%">
            <el-input v-model="form1.account" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label="姓名" label-width="15%">
            <el-input v-model="form1.name" autocomplete="off" style="width: 90%"></el-input>
          </el-form-item>
          <el-form-item label-width="15%">
            <el-input v-model="form1.cell" autocomplete="off" style="width: 20%"></el-input>&nbsp; 小区
            <el-input v-model="form1.building" autocomplete="off" style="width: 20%"></el-input>&nbsp; 楼&nbsp;
            <el-input v-model="form1.gate" autocomplete="off" style="width: 20%"></el-input>&nbsp; 门牌号
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submit()">确 定</el-button>
          <el-button @click="dialogFormVisible = false">取 消</el-button>

        </div>
      </el-dialog>
    </div>
  </div>

</template>
<script>
import request from "@/utils/request";

export default {

  data() {
    return {
      form:{},
      form1:{},
      imgSrc:require('../../img/t01589f692a7794f133.jpg'),
      key: '',
      captchaUrl: '',
      dialogFormVisible: false,
    }
  },
  //已经将编译好的模板，挂载到页面指定的容器中
  mounted() {
    this.key = Math.random();
    this.captchaUrl = 'http://localhost:8080/captcha?key=' + this.key;
  },
  // 定义一些页面上控件触发的事件调用的方法
  methods:{
    login() {
      this.form.role = "user"
      console.log(this.form.verCode)
      if (this.form.verCode !== undefined)
      {
        request.post("/user/login?key=" + this.key, this.form).then(res => {
          if (res.code === '0') {
            this.$message.success('登陆成功')
            // 利用网页的Application里面的localStorage存储登录的用户信息JSON.stringify把res.data转成字符串
            localStorage.setItem("user",JSON.stringify(res.data));
            this.$router.push("/UserLayout/home");
          } else {
            this.$message.error(res.msg)
            this.key = Math.random();
            this.captchaUrl = 'http://localhost:8080/captcha?key=' + this.key;
          }
        })
      } else {
        this.$message.error("验证码不能为空")
      }

    },
    clickImg() {
      this.key = Math.random();
      this.captchaUrl = 'http://localhost:8080/captcha?key=' + this.key;
    },
    reg() {
      this.form1 = {};
      this.dialogFormVisible = true;
    },
    submit() {
      this.form1.address = this.form1.cell + '小区' +  this.form1.building + '号楼' + this.form1.gate
      console.log(this.form1)
      request.post("/user/register", this.form1).then(res => {
        if (res.code === '0') {
          this.$message.success('注册成功')
          this.dialogFormVisible = false;
        } else {
          this.$message.error(res.msg)
        }
      })

    }
  }

}
</script>

<style scoped>
.login_container{
  background-color:palegreen;

}
.login_box{
  width: 560px;
  height: 350px;
  /* background-color: #ffffff; */
  background: rgba(255,255,255,0.3);
  border: 0px solid black;
  /*制定一个元素的不透明度*/
  opacity: 0.85;
  filter: alpha(opacity=90);
  border-radius: 20px;
  position: absolute;
  left: 50%;
  top:50%;
  transform: translate(-50%,-50%);

}
.avatar_box{
  width: 130px;
  height: 130px;
  border:-30px solid #eee;
  border-radius: 50%;
  padding: 0px;
  box-shadow: 0 0 2px #ddd;
  left:50%;

}
.btns{
  display: flex;
  justify-content: space-around;
}
.login_form{
  position: absolute;
  bottom: 0%;
  width: 100%;
  border: 30px;
}
.background{
  width:100%;
  height:100%;  /**宽高100%是为了图片铺满屏幕 */
  z-index:-1;
  position: absolute;
}
.in{
  margin-left: 10%;
  margin-right: 10%;
  width: 350px;
}
</style>