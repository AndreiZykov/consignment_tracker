<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring />
<@c.page>

    <@spring.bind "loginForm"/>

    <form action="/login" class="form-signin" method="post" enctype="utf8">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <#if spring.status.error>
            <ul>
                <#list spring.status.errors.globalErrors as error>
                    <div class="alert alert-primary" role="alert" >
                        ${error.defaultMessage}
                    </div>
                    <li></li>
                </#list>
            </ul>
        </#if>


        <label for="username" class="sr-only">Username(Email address)</label>
        <input name="username" type="email" id="username" class="form-control" placeholder="Email address" required
               autofocus>



        <label for="password" class="sr-only">Password</label>
        <input name="password" type="password" id="password" class="form-control" placeholder="Password" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</@c.page>