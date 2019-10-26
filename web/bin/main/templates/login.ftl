<#import "parts/common.ftl" as c>
<@c.page>
    <form action="/login" class="form-signin"  method="post" enctype="utf8">
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>

        <#--<div th:if="${error != null}">-->
            <#--<div  class="alert alert-primary" role="alert" th:text="${error}">-->
            <#--</div>-->
        <#--</div>-->

        <label for="username" class="sr-only">Username(Email address)</label>
        <input name="username" type="email" id="username" class="form-control"
               placeholder="Email address" required autofocus>
        <#if usernameError??>
            <div class="invalid-feedback">
                ${usernameError}
            </div>
        </#if>

        <label for="password" class="sr-only">Password</label>
        <input name="password" type="password" id="password" class="form-control"
               placeholder="Password" required>

        <#if error??>
            <div class="invalid-feedback">
                ${error}
            </div>
        </#if>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</@c.page>