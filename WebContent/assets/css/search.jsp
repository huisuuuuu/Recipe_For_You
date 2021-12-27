
<style>
div {
    box-sizing: border-box;
}

#wrapper {
    width: 100%;
    background-color: #fff;

}

.header {
    position: relative;
    width: 100%;
    height: 200px;
    border-bottom: 1px solid #E0E0E0;
}

.top {
    width: 100%;
    height: 35px;
    background-color: #5c9970;
    text-align: center;
}

.top-img {
    display: inline-block;
}

.top1 {
    font-family: 'Noto Sans KR', sans-serif;
    font-size: 13px;
    line-height: 35px;
    color: #fff;
}


.header1 {
    width: 100%;
    max-width: 1280px;
    height: 105px;
    text-align: center;
    margin: 0 auto;
    position: relative;
    padding: 30px 0;
}

.logo {
    width: 15.9%;
    height: 47px;
}

.box-user {
    position: absolute;
    top: 40px;
    right: 0;
}

.mypage {
    display: inline-block;
    margin-right: 35px;
}

.login {
    display: inline-block;
}

.navibar {
    width: 100%;
    max-width: 1280px;
    height: 60px;
    position: relative;
    padding: 0;
}

.navbar {
    width: 50%;
    padding: 0;
    float: left;
}

.navdistance {
    width: 100%;
    height: 100%;
}

.nav-item {
    color: #5E5E5E;
    font-family: 'Noto Sans KR', sans-serif;
    font-size: 15px;
    font-weight: bold;
    line-height: 55px;
    letter-spacing: 1.8px;
    text-align: center;
}

.nav-about {
    width: 100px;
    height: 60px;

    color: #5E5E5E;
}

.nav-recipe {
    width: 100px;
    height: 60px;
    color: #5E5E5E;
}

.nav-info {
    width: 100px;
    height: 60px;
    color: #5E5E5E;
}

.nav-mypage {
    width: 100px;
    height: 60px;
    color: #7FB292;
    margin-left: 20px;
    position: relative;
}

.nav-mypage:hover {
    color: #7FB292;
}

.nav-mypage:before {
    content: "";
    display: block;
    width: 2px;
    height: 30px;
    background-color: #E0E0E0;
    position: absolute;
    left: -15px;
    bottom: 8px;
    z-index: 999;
}

.nav-item:hover > a {
    color: #7FB292;
}

.nav-item:hover > .submenu {
    width: 100%;
    height: 100%;
    transition-property: height;
    transition-duration: 1s;
    border: 1px solid #5E5E5E;
}

.nav-item:hover > .submenu > li > a:hover {
    color: #7FB292;
}

.submenu {
    margin-right: 10px;
    padding: 0px;
    height: 0px;
    overflow: hidden;
    transition-property: height;
    background-color: white;
}

.submenu > li {
    list-style: none;
}

.submenu > li > a {
    display: block;
    width: 100%;
    height: 30px;
    text-decoration: none;
    text-align: center;
    font-family: 'Noto Sans KR', sans-serif;
    font-size: 14px;
    margin-top: 10px;
    color: #5E5E5E;
}

.nav-line {
    height: 26px;
    float: left;
    position: absolute;
    top: 40%;
    left: 30%;
}

.box-search {
    width: 50%
}


.searchbar {
    position: relative;
    margin-bottom: auto;
    margin-top: 20px;
    margin-left: 0px;
    width: 100%;
    height: 30px;
    border-radius: 30px;
    border-color: #7FB292;
    border-style: solid;
}

.search_input {
    color: black;
    border: 0;
    outline: 0;
    background: none;
    border-color: #7FB292;
    width: 100%;
    caret-color: transparent;
    line-height: 25px;
    padding: 0 10px;
}

.search_icon {
    height: 12px;
    width: 10px;
    position: absolute;
    right: 3%;
    border-radius: 50%;
    color: #7FB292;
    text-decoration: none;
}

.form-control {
    width: 600px;
    border-radius: 20px;
    20px;
    margin: 10px 0 0 0;
}



.contetns-sub {
    width: 100%;
    max-width: 1280px;
    margin: 15px auto 40px auto;

}



.contents2-title {
    width: 26.56%;
    margin: 61px 470px 20px 470px;
    text-align: center;
    font-size: 20px;
    font-family: 'Noto Serif KR', sans-serif;
    font-weight: 700;
    letter-spacing: 0.255em;
}

.contents2-button-more {
    width: 6.25%;
    height: 40px;
    border: 1px solid #7FB292;
    text-align: center;
    padding-top: 8px;
}

.contents2-button-more > a {
    color: #7FB292;
    font-size: 14px;
}




.footer {
    width: 100%;
    height: 200px;
    border-top: 1px solid #E0E0E0;
}

.footer1 {
    max-width: 1280px;
    margin-top: 20px;
}

.footer1 > pre {
    font-size: 13px;
    text-align: left;
    font-family: 'Noto Sans KR', sans-serif;
}

.footer-bottom {
    width: 100%;
    background-color: #E4E4E4;
}

#footer-word > pre {
    max-width: 1280px;
    margin: 0 auto;
    text-align: left;
    font-size: 13px;
    font-family: 'Noto Sans KR', sans-serif;

}
</style>