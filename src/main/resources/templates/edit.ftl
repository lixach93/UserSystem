<#import "/spring.ftl" as spring />
<#import "general.ftl" as g />

<@g.page>

    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form action="/user/new" method="post">
                <div class="form-group">
                    <@spring.bind "userForm.userName"/>
                    <label for="userName">User name: </label>
                    <input type="text" id="userName" class="form-control" name="userName" value="${spring.status.value?default("")}">
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
                            <#if userForm.role == roleType>
                                <option selected>
                                    ${roleType.name()}
                                </option>
                            <#else>
                            <option>
                                ${roleType.name()}
                            </option>
                            </#if>
                        </#list>
                    </select>
                </div>

                <div class="form-group">
                    <@spring.bind "userForm.status"/>
                    <label for="status">Status : </label>
                    <select id="status" class="form-control" name="status">
                        <#list statusTypes as statusType>
                            <#if userForm.status == statusType>
                                <option selected>
                                    ${statusType.name()}
                                </option>
                            <#else>
                                <option>
                                    ${statusType.name()}
                                </option>
                            </#if>
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