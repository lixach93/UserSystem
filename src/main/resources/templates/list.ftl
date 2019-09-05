<#import "/spring.ftl" as spring />
<#import "layouts/general.ftl" as g />
<#import "pages.ftl" as p/>

<@g.page>
    <@p.pager url page/>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>UserName</th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Role</th>
            <th>Status</th>
            <th>Created At</th>
        </tr>
        </thead>
        <tbody>
        <#list page.content as user>
            <tr>
                <td>
                    <a href="/user/${user.id}">${user.id}</a>
                </td>
                <td>${user.userName}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.role}</td>
                <td>${user.status}</td>
                <td>${user.createdDate}</td>
            </tr>
        </#list>
        </tbody>
    </table>



</@g.page>
