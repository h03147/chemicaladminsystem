(function(e){function n(n){for(var a,r,o=n[0],u=n[1],i=n[2],d=0,f=[];d<o.length;d++)r=o[d],Object.prototype.hasOwnProperty.call(c,r)&&c[r]&&f.push(c[r][0]),c[r]=0;for(a in u)Object.prototype.hasOwnProperty.call(u,a)&&(e[a]=u[a]);l&&l(n);while(f.length)f.shift()();return s.push.apply(s,i||[]),t()}function t(){for(var e,n=0;n<s.length;n++){for(var t=s[n],a=!0,r=1;r<t.length;r++){var o=t[r];0!==c[o]&&(a=!1)}a&&(s.splice(n--,1),e=u(u.s=t[0]))}return e}var a={},r={app:0},c={app:0},s=[];function o(e){return u.p+"js/"+({about:"about"}[e]||e)+"."+{about:"d2de17df","chunk-03846c98":"969028b8","chunk-0e089f9e":"b35eef36","chunk-15f03802":"b2f5b0d0","chunk-1699dcac":"358eefce","chunk-632e073e":"5e57e49d","chunk-75c28df6":"95f2cadf","chunk-1fe038a2":"c7d5edc4","chunk-2a4541e3":"e618d8bc","chunk-341a6989":"7e13d6af","chunk-50c7a878":"07f06a67","chunk-52709c3d":"c18ffd16","chunk-5e45ec2e":"00fea9e2","chunk-6ff127f8":"bb9f036f","chunk-75ba1d22":"2d894760","chunk-7a16fae7":"632f4895","chunk-8baef7e4":"f1f4dabc","chunk-8bd0376e":"66b58990","chunk-c1f2dcde":"10edacd9","chunk-f973145c":"993e34b8","chunk-fbc4d332":"b8f95770","chunk-5af1855c":"f11aaac3"}[e]+".js"}function u(n){if(a[n])return a[n].exports;var t=a[n]={i:n,l:!1,exports:{}};return e[n].call(t.exports,t,t.exports,u),t.l=!0,t.exports}u.e=function(e){var n=[],t={about:1,"chunk-03846c98":1,"chunk-0e089f9e":1,"chunk-15f03802":1,"chunk-632e073e":1,"chunk-75c28df6":1,"chunk-1fe038a2":1,"chunk-2a4541e3":1,"chunk-341a6989":1,"chunk-50c7a878":1,"chunk-52709c3d":1,"chunk-5e45ec2e":1,"chunk-6ff127f8":1,"chunk-75ba1d22":1,"chunk-7a16fae7":1,"chunk-8baef7e4":1,"chunk-8bd0376e":1,"chunk-c1f2dcde":1,"chunk-f973145c":1,"chunk-fbc4d332":1};r[e]?n.push(r[e]):0!==r[e]&&t[e]&&n.push(r[e]=new Promise((function(n,t){for(var a="css/"+({about:"about"}[e]||e)+"."+{about:"ea9848b8","chunk-03846c98":"42fc0000","chunk-0e089f9e":"58f3ba33","chunk-15f03802":"99d9d548","chunk-1699dcac":"31d6cfe0","chunk-632e073e":"fe074b6f","chunk-75c28df6":"b8fa84ad","chunk-1fe038a2":"8d8d1875","chunk-2a4541e3":"519f3a96","chunk-341a6989":"7d1ce50c","chunk-50c7a878":"000c8f9c","chunk-52709c3d":"32e35de0","chunk-5e45ec2e":"5431084d","chunk-6ff127f8":"2f56dccb","chunk-75ba1d22":"9e7f15e6","chunk-7a16fae7":"99b452cf","chunk-8baef7e4":"d76d0932","chunk-8bd0376e":"9c55922b","chunk-c1f2dcde":"1a66c7e5","chunk-f973145c":"55fbe156","chunk-fbc4d332":"774c5968","chunk-5af1855c":"31d6cfe0"}[e]+".css",c=u.p+a,s=document.getElementsByTagName("link"),o=0;o<s.length;o++){var i=s[o],d=i.getAttribute("data-href")||i.getAttribute("href");if("stylesheet"===i.rel&&(d===a||d===c))return n()}var f=document.getElementsByTagName("style");for(o=0;o<f.length;o++){i=f[o],d=i.getAttribute("data-href");if(d===a||d===c)return n()}var l=document.createElement("link");l.rel="stylesheet",l.type="text/css",l.onload=n,l.onerror=function(n){var a=n&&n.target&&n.target.src||c,s=new Error("Loading CSS chunk "+e+" failed.\n("+a+")");s.code="CSS_CHUNK_LOAD_FAILED",s.request=a,delete r[e],l.parentNode.removeChild(l),t(s)},l.href=c;var b=document.getElementsByTagName("head")[0];b.appendChild(l)})).then((function(){r[e]=0})));var a=c[e];if(0!==a)if(a)n.push(a[2]);else{var s=new Promise((function(n,t){a=c[e]=[n,t]}));n.push(a[2]=s);var i,d=document.createElement("script");d.charset="utf-8",d.timeout=120,u.nc&&d.setAttribute("nonce",u.nc),d.src=o(e);var f=new Error;i=function(n){d.onerror=d.onload=null,clearTimeout(l);var t=c[e];if(0!==t){if(t){var a=n&&("load"===n.type?"missing":n.type),r=n&&n.target&&n.target.src;f.message="Loading chunk "+e+" failed.\n("+a+": "+r+")",f.name="ChunkLoadError",f.type=a,f.request=r,t[1](f)}c[e]=void 0}};var l=setTimeout((function(){i({type:"timeout",target:d})}),12e4);d.onerror=d.onload=i,document.head.appendChild(d)}return Promise.all(n)},u.m=e,u.c=a,u.d=function(e,n,t){u.o(e,n)||Object.defineProperty(e,n,{enumerable:!0,get:t})},u.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},u.t=function(e,n){if(1&n&&(e=u(e)),8&n)return e;if(4&n&&"object"===typeof e&&e&&e.__esModule)return e;var t=Object.create(null);if(u.r(t),Object.defineProperty(t,"default",{enumerable:!0,value:e}),2&n&&"string"!=typeof e)for(var a in e)u.d(t,a,function(n){return e[n]}.bind(null,a));return t},u.n=function(e){var n=e&&e.__esModule?function(){return e["default"]}:function(){return e};return u.d(n,"a",n),n},u.o=function(e,n){return Object.prototype.hasOwnProperty.call(e,n)},u.p="/",u.oe=function(e){throw console.error(e),e};var i=window["webpackJsonp"]=window["webpackJsonp"]||[],d=i.push.bind(i);i.push=n,i=i.slice();for(var f=0;f<i.length;f++)n(i[f]);var l=d;s.push([0,"chunk-vendors"]),t()})({0:function(e,n,t){e.exports=t("56d7")},"034f":function(e,n,t){"use strict";var a=t("85ec"),r=t.n(a);r.a},"0a1d":function(e,n,t){"use strict";var a=t("e77c"),r=t.n(a);r.a},2144:function(e,n,t){"use strict";t.r(n);var a=function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("div",[t("div",{staticClass:"login-container"},[t("el-row",{staticClass:"elrow1bigtitlediv1"},[t("span",[e._v(" "+e._s(e.Bigtitle)+" ")])]),t("el-form",{ref:"loginForm",staticClass:"login-form",attrs:{model:e.loginForm,rules:e.loginRules,"auto-complete":"on","label-position":"left"}},[t("div",{staticClass:"title-container"},[t("h3",{staticClass:"title"},[e._v("欢迎登录")])]),t("el-form-item",{attrs:{prop:"usernumber"}},[t("el-input",{ref:"usernumber",attrs:{placeholder:"请输入账号",name:"usernumber",type:"text",tabindex:"1","auto-complete":"on"},model:{value:e.loginForm.usernumber,callback:function(n){e.$set(e.loginForm,"usernumber",n)},expression:"loginForm.usernumber"}},[t("i",{staticClass:"el-input__icon el-icon-user",attrs:{slot:"prefix"},slot:"prefix"})])],1),t("el-form-item",{attrs:{prop:"password"}},[t("el-input",{key:e.passwordType,ref:"password",attrs:{type:e.passwordType,placeholder:"请输入密码",name:"password",tabindex:"2","auto-complete":"on"},nativeOn:{keyup:function(n){return!n.type.indexOf("key")&&e._k(n.keyCode,"enter",13,n.key,"Enter")?null:e.handleLogin(n)}},model:{value:e.loginForm.password,callback:function(n){e.$set(e.loginForm,"password",n)},expression:"loginForm.password"}},[t("i",{staticClass:"el-input__icon el-icon-lock",attrs:{slot:"prefix"},slot:"prefix"})])],1),t("el-form-item",{staticClass:"elformitemrole",attrs:{prop:"userrole"}},[t("el-select",{staticClass:"elselectidentity",attrs:{placeholder:"权限选择"},model:{value:e.loginForm.userrole,callback:function(n){e.$set(e.loginForm,"userrole",n)},expression:"loginForm.userrole"}},[t("el-option",{attrs:{label:"高级管理员",value:"高级管理员"}}),t("el-option",{attrs:{label:"普通管理员",value:"普通管理员"}}),t("i",{staticClass:"el-input__icon el-icon-postcard",attrs:{slot:"prefix"},slot:"prefix"})],1)],1),t("el-button",{staticStyle:{width:"100%","margin-bottom":"30px"},attrs:{type:"primary"},nativeOn:{click:function(n){return n.preventDefault(),e.handleLogin(n)}}},[e._v("登录")])],1)],1)])},r=[],c=(t("96cf"),t("1da1")),s=t("bc3a"),o=t.n(s),u=t("72ac"),i=o.a.create({baseURL:u["a"].baseURL,timeout:1e3,headers:{"X-Custom-Header":"foobar"}}),d={name:"index",data:function(){return{loginForm:{usernumber:"",password:"",userrole:""},loginRules:{usernumber:[{required:!0,trigger:"blur",message:"用户名不能为空！"}],password:[{required:!0,message:"密码不能为空！",trigger:"blur"},{min:6,max:50,message:"长度不能低于6位",trigger:"blur"}],userrole:[{required:!0,trigger:"blur",message:"请选择权限！"}]},passwordType:"password",api1:this.GLOBAL.baseURL,Bigtitle:"化学品物资管理系统"}},methods:{handleLogin:function(){var e=this;this.$refs.loginForm.validate((function(n){if(!n)return console.log("error submit!!"),!1;e.postLogin()}))},postLogin:function(){var e=this;return Object(c["a"])(regeneratorRuntime.mark((function n(){var t,a;return regeneratorRuntime.wrap((function(n){while(1)switch(n.prev=n.next){case 0:return n.next=2,i.post("/login",e.loginForm);case 2:t=n.sent,a=t.data,a.state?(i.post("/Loginlog/saveOneHistory",e.loginForm),sessionStorage.setItem("token",a.token),sessionStorage.setItem("user_number",e.loginForm.usernumber),"高级管理员"==e.loginForm.userrole?(e.$message.success("高级管理员登录成功"),e.$router.push("/homepage/page1index1")):(e.$message.success("普通管理员登录成功"),e.$router.push("/homepage/page1index1"))):e.$message.error(a.msg);case 5:case"end":return n.stop()}}),n)})))()}}},f=d,l=(t("817d"),t("0a1d"),t("2877")),b=Object(l["a"])(f,a,r,!1,null,"e1f58716",null);n["default"]=b.exports},"2b23":function(e,n,t){},"404f":function(e,n,t){},4678:function(e,n,t){var a={"./af":"2bfb","./af.js":"2bfb","./ar":"8e73","./ar-dz":"a356","./ar-dz.js":"a356","./ar-kw":"423e","./ar-kw.js":"423e","./ar-ly":"1cfd","./ar-ly.js":"1cfd","./ar-ma":"0a84","./ar-ma.js":"0a84","./ar-sa":"8230","./ar-sa.js":"8230","./ar-tn":"6d83","./ar-tn.js":"6d83","./ar.js":"8e73","./az":"485c","./az.js":"485c","./be":"1fc1","./be.js":"1fc1","./bg":"84aa","./bg.js":"84aa","./bm":"a7fa","./bm.js":"a7fa","./bn":"9043","./bn-bd":"9686","./bn-bd.js":"9686","./bn.js":"9043","./bo":"d26a","./bo.js":"d26a","./br":"6887","./br.js":"6887","./bs":"2554","./bs.js":"2554","./ca":"d716","./ca.js":"d716","./cs":"3c0d","./cs.js":"3c0d","./cv":"03ec","./cv.js":"03ec","./cy":"9797","./cy.js":"9797","./da":"0f14","./da.js":"0f14","./de":"b469","./de-at":"b3eb","./de-at.js":"b3eb","./de-ch":"bb71","./de-ch.js":"bb71","./de.js":"b469","./dv":"598a","./dv.js":"598a","./el":"8d47","./el.js":"8d47","./en-au":"0e6b","./en-au.js":"0e6b","./en-ca":"3886","./en-ca.js":"3886","./en-gb":"39a6","./en-gb.js":"39a6","./en-ie":"e1d3","./en-ie.js":"e1d3","./en-il":"7333","./en-il.js":"7333","./en-in":"ec2e","./en-in.js":"ec2e","./en-nz":"6f50","./en-nz.js":"6f50","./en-sg":"b7e9","./en-sg.js":"b7e9","./eo":"65db","./eo.js":"65db","./es":"898b","./es-do":"0a3c","./es-do.js":"0a3c","./es-mx":"b5b7","./es-mx.js":"b5b7","./es-us":"55c9","./es-us.js":"55c9","./es.js":"898b","./et":"ec18","./et.js":"ec18","./eu":"0ff2","./eu.js":"0ff2","./fa":"8df4","./fa.js":"8df4","./fi":"81e9","./fi.js":"81e9","./fil":"d69a","./fil.js":"d69a","./fo":"0721","./fo.js":"0721","./fr":"9f26","./fr-ca":"d9f8","./fr-ca.js":"d9f8","./fr-ch":"0e49","./fr-ch.js":"0e49","./fr.js":"9f26","./fy":"7118","./fy.js":"7118","./ga":"5120","./ga.js":"5120","./gd":"f6b4","./gd.js":"f6b4","./gl":"8840","./gl.js":"8840","./gom-deva":"aaf2","./gom-deva.js":"aaf2","./gom-latn":"0caa","./gom-latn.js":"0caa","./gu":"e0c5","./gu.js":"e0c5","./he":"c7aa","./he.js":"c7aa","./hi":"dc4d","./hi.js":"dc4d","./hr":"4ba9","./hr.js":"4ba9","./hu":"5b14","./hu.js":"5b14","./hy-am":"d6b6","./hy-am.js":"d6b6","./id":"5038","./id.js":"5038","./is":"0558","./is.js":"0558","./it":"6e98","./it-ch":"6f12","./it-ch.js":"6f12","./it.js":"6e98","./ja":"079e","./ja.js":"079e","./jv":"b540","./jv.js":"b540","./ka":"201b","./ka.js":"201b","./kk":"6d79","./kk.js":"6d79","./km":"e81d","./km.js":"e81d","./kn":"3e92","./kn.js":"3e92","./ko":"22f8","./ko.js":"22f8","./ku":"2421","./ku.js":"2421","./ky":"9609","./ky.js":"9609","./lb":"440c","./lb.js":"440c","./lo":"b29d","./lo.js":"b29d","./lt":"26f9","./lt.js":"26f9","./lv":"b97c","./lv.js":"b97c","./me":"293c","./me.js":"293c","./mi":"688b","./mi.js":"688b","./mk":"6909","./mk.js":"6909","./ml":"02fb","./ml.js":"02fb","./mn":"958b","./mn.js":"958b","./mr":"39bd","./mr.js":"39bd","./ms":"ebe4","./ms-my":"6403","./ms-my.js":"6403","./ms.js":"ebe4","./mt":"1b45","./mt.js":"1b45","./my":"8689","./my.js":"8689","./nb":"6ce3","./nb.js":"6ce3","./ne":"3a39","./ne.js":"3a39","./nl":"facd","./nl-be":"db29","./nl-be.js":"db29","./nl.js":"facd","./nn":"b84c","./nn.js":"b84c","./oc-lnc":"167b","./oc-lnc.js":"167b","./pa-in":"f3ff","./pa-in.js":"f3ff","./pl":"8d57","./pl.js":"8d57","./pt":"f260","./pt-br":"d2d4","./pt-br.js":"d2d4","./pt.js":"f260","./ro":"972c","./ro.js":"972c","./ru":"957c","./ru.js":"957c","./sd":"6784","./sd.js":"6784","./se":"ffff","./se.js":"ffff","./si":"eda5","./si.js":"eda5","./sk":"7be6","./sk.js":"7be6","./sl":"8155","./sl.js":"8155","./sq":"c8f3","./sq.js":"c8f3","./sr":"cf1e","./sr-cyrl":"13e9","./sr-cyrl.js":"13e9","./sr.js":"cf1e","./ss":"52bd","./ss.js":"52bd","./sv":"5fbd","./sv.js":"5fbd","./sw":"74dc","./sw.js":"74dc","./ta":"3de5","./ta.js":"3de5","./te":"5cbb","./te.js":"5cbb","./tet":"576c","./tet.js":"576c","./tg":"3b1b","./tg.js":"3b1b","./th":"10e8","./th.js":"10e8","./tk":"5aff","./tk.js":"5aff","./tl-ph":"0f38","./tl-ph.js":"0f38","./tlh":"cf75","./tlh.js":"cf75","./tr":"0e81","./tr.js":"0e81","./tzl":"cf51","./tzl.js":"cf51","./tzm":"c109","./tzm-latn":"b53d","./tzm-latn.js":"b53d","./tzm.js":"c109","./ug-cn":"6117","./ug-cn.js":"6117","./uk":"ada2","./uk.js":"ada2","./ur":"5294","./ur.js":"5294","./uz":"2e8c","./uz-latn":"010e","./uz-latn.js":"010e","./uz.js":"2e8c","./vi":"2921","./vi.js":"2921","./x-pseudo":"fd7e","./x-pseudo.js":"fd7e","./yo":"7f33","./yo.js":"7f33","./zh-cn":"5c3a","./zh-cn.js":"5c3a","./zh-hk":"49ab","./zh-hk.js":"49ab","./zh-mo":"3a6c","./zh-mo.js":"3a6c","./zh-tw":"90ea","./zh-tw.js":"90ea"};function r(e){var n=c(e);return t(n)}function c(e){if(!t.o(a,e)){var n=new Error("Cannot find module '"+e+"'");throw n.code="MODULE_NOT_FOUND",n}return a[e]}r.keys=function(){return Object.keys(a)},r.resolve=c,e.exports=r,r.id="4678"},5315:function(e,n,t){},"56d7":function(e,n,t){"use strict";t.r(n);t("4de4"),t("e260"),t("e6cf"),t("cca6"),t("a79d");var a=t("2b0e"),r=(t("d3b7"),t("bc3a")),c=t.n(r),s={},o=c.a.create(s);o.interceptors.request.use((function(e){return e}),(function(e){return Promise.reject(e)})),o.interceptors.response.use((function(e){return e}),(function(e){return Promise.reject(e)})),Plugin.install=function(e,n){e.axios=o,window.axios=o,Object.defineProperties(e.prototype,{axios:{get:function(){return o}},$axios:{get:function(){return o}}})},a["default"].use(Plugin);Plugin,t("b20f");var u=function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},i=[],d=function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("div",{staticClass:"hello"},[t("Index")],1)},f=[],l=t("2144"),b={name:"HelloWorld",components:{Index:l["default"]},props:{msg:String}},p=b,h=(t("6dfb"),t("2877")),m=Object(h["a"])(p,d,f,!1,null,"d5fe2a50",null),g=m.exports,j={name:"app"},k=j,v=(t("034f"),Object(h["a"])(k,u,i,!1,null,null,null)),x=v.exports,y=t("8c4f"),w=function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("div",{staticClass:"home"},[t("HelloWorld",{attrs:{msg:"Welcome to Your Vue.js App"}})],1)},P=[],_={name:"Home",components:{HelloWorld:g}},O=_,z=Object(h["a"])(O,w,P,!1,null,null,null),C=z.exports;a["default"].use(y["a"]);var F=[{path:"/",name:"Home",component:C},{path:"/about",name:"About",component:function(){return t.e("about").then(t.bind(null,"f820"))}},{path:"/signin",name:"Signin",component:function(){return t.e("about").then(t.bind(null,"2144"))}},{path:"/homepage",name:"HomePage",component:function(){return t.e("about").then(t.bind(null,"f572"))},children:[{path:"page1index1",name:"Page1Index1",component:function(){return t.e("chunk-341a6989").then(t.bind(null,"f545"))}},{path:"page2index1",name:"Page2Index1",component:function(){return t.e("chunk-52709c3d").then(t.bind(null,"c225"))}},{path:"/page2update1",name:"Page2Update1",component:function(){return t.e("chunk-75ba1d22").then(t.bind(null,"b07a"))}},{path:"page3index1",name:"Page3Index1",component:function(){return Promise.all([t.e("chunk-1699dcac"),t.e("chunk-75c28df6")]).then(t.bind(null,"4ad1"))}},{path:"page3index2",name:"Page3Index2",component:function(){return Promise.all([t.e("chunk-1699dcac"),t.e("chunk-632e073e")]).then(t.bind(null,"1070"))}},{path:"/page3update1",name:"Page3Update1",component:function(){return t.e("chunk-f973145c").then(t.bind(null,"4d33"))}},{path:"page4index1",name:"Page4Index1",component:function(){return t.e("chunk-50c7a878").then(t.bind(null,"1f3b"))}},{path:"page4index2",name:"Page4Index2",component:function(){return t.e("chunk-8baef7e4").then(t.bind(null,"8b1d"))}},{path:"page4index3",name:"Page4Index3",component:function(){return t.e("chunk-2a4541e3").then(t.bind(null,"9166"))}},{path:"/page4adddate1",name:"Page4Adddate1",component:function(){return t.e("chunk-03846c98").then(t.bind(null,"f71d"))}},{path:"/page4adddate2",name:"Page4Adddate2",component:function(){return t.e("chunk-6ff127f8").then(t.bind(null,"9637"))}},{path:"/page4update1",name:"Page4Update1",component:function(){return t.e("chunk-0e089f9e").then(t.bind(null,"f912"))}},{path:"/page4update2",name:"Page4Update2",component:function(){return t.e("chunk-15f03802").then(t.bind(null,"ad1b"))}},{path:"page5index1",name:"Page5Index1",component:function(){return t.e("chunk-7a16fae7").then(t.bind(null,"cf97"))}},{path:"page5index2",name:"Page5Index2",component:function(){return t.e("chunk-5e45ec2e").then(t.bind(null,"4131"))}},{path:"/page5exportdate1",name:"Page5Exportdate1",component:function(){return t.e("chunk-fbc4d332").then(t.bind(null,"9142"))}},{path:"/page5exportdate2",name:"Page5Exportdate2",component:function(){return t.e("chunk-1fe038a2").then(t.bind(null,"7107"))}},{path:"page6index1",name:"Page6Index1",component:function(){return t.e("chunk-c1f2dcde").then(t.bind(null,"f8a9"))}},{path:"page7index1",name:"Page7Index1",component:function(){return t.e("chunk-8bd0376e").then(t.bind(null,"029b"))}}]}],L=new y["a"]({mode:"history",base:"/",routes:F}),E=L,I=t("2f62");a["default"].use(I["a"]);var S=new I["a"].Store({state:{},mutations:{},actions:{},modules:{}}),T=t("5c96"),$=t.n(T);t("0fae");a["default"].use($.a);var A=t("72ac"),H=(t("404f"),t("c1df")),U=t.n(H);a["default"].prototype.GLOBAL=A["a"],a["default"].config.productionTip=!1,a["default"].filter("dateformat",(function(e){var n=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"YYYY-MM-DD HH:mm:ss";return U()(e).format(n)})),new a["default"]({router:E,store:S,render:function(e){return e(x)}}).$mount("#app")},"6dfb":function(e,n,t){"use strict";var a=t("2b23"),r=t.n(a);r.a},"72ac":function(e,n,t){"use strict";var a="http://127.0.0.1:9528",r="123456",c="林花落了春红，太匆匆";n["a"]={baseURL:a,token:r,userSite:c}},"817d":function(e,n,t){"use strict";var a=t("5315"),r=t.n(a);r.a},"85ec":function(e,n,t){},b20f:function(e,n,t){e.exports={menuText:"#bfcbd9",menuActiveText:"#409EFF",subMenuActiveText:"#f4f4f5",menuBg:"#304156",menuHover:"#263445",subMenuBg:"#1f2d3d",subMenuHover:"#001528",sideBarWidth:"210px"}},e77c:function(e,n,t){}});
//# sourceMappingURL=app.da9f397b.js.map