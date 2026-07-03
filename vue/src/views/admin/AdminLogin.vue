<template>
  <div class="background">
    <div class="background">
      <img :src="imgSrc" width="100%" height="937px" alt="" />
    </div>
    <div class="login_container">
      <div class="login_box">
        <div style="font-size: 30px; line-height: 150px; text-align: center; color: #000000">欢迎登录</div>
        <div style="text-align: center">
          <el-form :model="admin" class="login_form">
            <el-form-item>
              <el-input v-model="admin.account" prefix-icon="el-icon-user" style="width: 80%"  class="in" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item>
              <el-input v-model="admin.password" show-password prefix-icon="el-icon-lock" style="width: 80%" placeholder="请输入密码名"></el-input>
            </el-form-item>
            <el-form-item class="btns">
              <el-button type="primary" @click="login()">登录</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  data() {
    return {
      admin:{

      },
      imgSrc:require('../../img/t01589f692a7794f133.jpg')
    }
  },
  created() {

  },
  methods: {
    /**
     * 管理员登陆
     */
    login() {
      this.admin.role = "admin"
      request.post("/admin/login", this.admin).then(res => {
        console.log(res)
        if (res.code === '0') {
          this.$message.success('登陆成功');
          localStorage.setItem("user", JSON.stringify(res.data));
          this.$router.push("/House_fb");
        } else {
          this.$message.error(res.msg);
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
  width: 450px;
  height: 300px;
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