<#import "parts/common.ftl" as c>
<@c.page>
    <div class="container">
        <form action="/registration"  method="post" enctype="utf8" id="reg">
            <h1 class="h3 mb-3 font-weight-normal">Sign up</h1>
            <div class="row">
                <div class="form-group col-md-4">
                    <label>Email</label>
                    <input name="email" type="email" class="form-control" placeholder="johndow@gmail.com" required autofocus>
                </div>
                <div class="form-group col-md-4">
                    <label>First Name</label>
                    <input type="text" name="firstName" class="form-control" placeholder="John">
                </div>
                <div class="form-group col-md-4">
                    <label>Last Name</label>
                    <input type="text" name="lastName" class="form-control" placeholder="Dow">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <label>Password</label>
                    <input type="password" name="password" class="form-control" placeholder="">
                </div>
                <div class="form-group col-md-6">
                    <label>Confirm Password</label>
                    <input type="password" name="confirmPassword" class="form-control" placeholder="">
                </div>
            </div>
            <input type="submit" class="btn btn-primary" value="Next">
        </form>
    </div>
</@c.page>