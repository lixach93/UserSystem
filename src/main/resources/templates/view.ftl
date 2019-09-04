<#import "/spring.ftl" as spring />
<#import "layouts/general.ftl" as g />
<#include "layouts/session.ftl">

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
            </div>
            <div class="col-sm-4"></div>
        </div>
        <div class="row">
            <div class="col-sm-4"></div>
                <#if isAdmin>
                    <div class="col-sm-1">
                        <form method="post" action="/user/${userAccount.id}">
                            <button type="submit" class="btn btn-primary">Lock/Unlock</button>
                        </form>
                    </div>
                    <div class="col-sm-2">
                        <a class="btn btn-primary" href="/user/${userAccount.id}/edit" role="button">Edit</a>
                    </div
                </#if>
            <div class="col-sm-4"></div>
        </div>
    </div>
</@g.page>