<#import "/spring.ftl" as spring />
<#import "layouts/general.ftl" as g />

<@g.page>

    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <#if error??>
                <h4>${error}</h4>git
            </#if>

            <form action="/user/new" method="post">
                <div class="form-group">
                    <@spring.bind "userForm.userName"/>
                    <label for="userName">User name: </label>
                    <input type="text" id="userName" class="form-control" name="userName" value="${spring.status.value?default("")}">
                    <small id="emailHelp" class="form-text text-muted"><@spring.showErrors "<br>"/></small>
                </div>

                <div class="form-group">
                    <@spring.bind "userForm.password"/>
                    <label for="password">Password: </label>
                    <input type="text" id="password" class="form-control" name="password" value="${spring.status.value?default("")}">
                    <small id="emailHelp" class="form-text text-muted"><@spring.showErrors "<br>"/></small>
                </div>

                <div class="form-group">
                    <@spring.bind "userForm.firstName"/>
                    <label for="firstName">First name: </label>
                    <input type="text" id="firstName" class="form-control" name="firstName" value="${spring.status.value?default("")}">
                    <small id="emailHelp" class="form-text text-muted"><@spring.showErrors "<br>"/></small>
                </div>

                <div class="form-group">
                    <@spring.bind "userForm.lastName"/>
                    <label for="lastName">Last name: </label>
                    <input type="text" id="lastName" class="form-control" name="lastName" value="${spring.status.value?default("")}">
                    <small id="emailHelp" class="form-text text-muted"><@spring.showErrors "<br>"/></small>
                </div>

                <div class="form-group">
                    <@spring.bind "userForm.role"/>
                    <label for="role">Role : </label>
                    <select id="role" class="form-control" name="role">
                        <#list roleTypes as roleType>
                            <option>
                                ${roleType.name()}
                            </option>
                        </#list>
                    </select>
                </div>

                <div class="form-group">
                    <@spring.bind "userForm.status"/>
                    <label for="status">Status : </label>
                    <select id="status" class="form-control" name="status">
                        <#list statusTypes as statusType>
                            <option>
                                ${statusType.name()}
                            </option>
                        </#list>
                    </select>

                </div>

                <div class="text-center">
                    <input type="submit" class="btn btn-success" value="Submit">
                </div>

            </form>
        </div>
    </div>



</@g.page>