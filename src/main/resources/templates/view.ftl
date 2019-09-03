<#import "/spring.ftl" as spring />
<#import "general.ftl" as g />
<#include "session.ftl">
<@g.page>
    <div class="container">
        <div class="row">
            <div class="col-sm-4"></div>
        <div class="col-sm-4">
        <h5>User name : ${userAccount.userName}</h5>
        <h5>First name : ${userAccount.firstName}</h5>
        <h5>Last name :${userAccount.lastName}</h5>
        <h5>Role :${userAccount.role}</h5>
        <h5>Status: ${userAccount.status}</h5>
        <h5>Created date : ${userAccount.createdDate}</h5>

           <#if isAdmin>
        <form method="post" action="/user/${userAccount.id}">
            <button type="submit" class="btn btn-primary">Lock/Unlock</button>
        </form>
           </#if>
        </div>
            <div class="col-sm-4"></div>

        </div>
    </div>
</@g.page>