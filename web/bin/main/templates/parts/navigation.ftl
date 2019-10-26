<!-- Navigation -->
<#include "security.ftl">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top" >

    <a class="navbar-brand" href="/">Pawn</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive" >
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/items/all">Items</a>
            </li>
            <#if user??>
            <#else>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>

            <li>
                <a class="nav-link" href="/registration">Register</a>
            </li>
            </#if>



            <#if user??>
            <li>
                <a class="nav-link" href="/logout">Logout</a>
            </li>
           </#if>

            <#if isAdmin>
            <li sec:authorize="hasRole('ROLE_ADMIN') or hasRole('ROLE_OWNER')">
                <a class="nav-link" href="/admin/">Dashboard</a>
            </li>
            </#if>

            <#if user??>
            <li >
                <a class="nav-link" href="/profile">Profile</a>
            </li>
            </#if>
        </ul>
    </div>

</nav>
