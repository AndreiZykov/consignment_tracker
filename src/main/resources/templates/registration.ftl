<#import "parts/common.ftl" as c>
<@c.page>
    <div class="container">

    <#if user?? >
        Your submitted data <br>
    <#else>
        <form action="/registration"  method="post" enctype="utf8" id="reg">
            <h1 class="h3 mb-3 font-weight-normal">Sign up</h1>

            <div class="row">
                <div class="form-group col-md-4">

                    <label>Username (must be a email
                        address)</label>
                    <input type="email" id="username" class="form-control" placeholder="Username""
                           required autofocus>
                    <#--<td th:if="${#fields.hasErrors('username')}" th:errors="*{username}">Name Error</td>-->
                </div>
                <div class="form-group col-md-4">
                    <label>First Name</label>
                    <input type="text" id="firstName" class="form-control" placeholder="First name">
                    <#--<td th:if="${#fields.hasErrors('firstName')}" th:errors="*{firstName}">Name Error</td>-->
                </div>
                <div class="form-group col-md-4">
                    <label>Last Name</label>
                    <input type="text" id="lastName" class="form-control" placeholder="Last Name">
                    <#--<td th:if="${#fields.hasErrors('lastName')}" th:errors="*{lastName}">Name Error</td>-->
                </div>
            </div>


            <div class="row">
                <div class="form-group col-md-6">
                    <label>Password</label>
                    <input type="password" id="password" class="form-control" placeholder="Password">
                    <#--<td th:if="${#fields.hasErrors('password')}" th:errors="*{password}">Name Error</td>-->
                </div>
                <div class="form-group col-md-6">
                    <label>Confirm Password</label>
                    <input type="password" id="confirmPassword" class="form-control" placeholder="Confirm Password">
                    <#--<td th:if="${#fields.hasErrors('confirmPassword')}" th:errors="*{confirmPassword}">Name Error</td>-->
                </div>
            </div>

            <input type="submit" class="btn btn-primary" value="Next">

        </form>
    </#if>
    </div>
</@c.page>