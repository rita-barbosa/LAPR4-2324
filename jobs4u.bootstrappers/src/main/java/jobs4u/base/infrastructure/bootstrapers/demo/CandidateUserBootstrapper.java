/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package jobs4u.base.infrastructure.bootstrapers.demo;

import jobs4u.base.infrastructure.bootstrapers.UsersBootstrapperBase;
import eapli.framework.actions.Action;

/**
 * @author Paulo Sousa
 */
public class CandidateUserBootstrapper extends UsersBootstrapperBase implements Action {
    private static final String PASSWORD1 = "Password-1";

    @Override
    public boolean execute() {

        return true;
    }
//
//    private void registerCandidate(final String email, final String password,
//                                         final String firstName, final String lastName) {
//        final Set<Role> roles = new HashSet<>();
//        roles.add(BaseRoles.CANDIDATE_USER);
//
//        registerUser(email, password, firstName, lastName, roles);
//    }
//
//    private void registerCustomer(final String email, final String password,
//                                  final String firstName, final String lastName) {
//        final Set<Role> roles = new HashSet<>();
//        roles.add(BaseRoles.CUSTOMER_USER);
//
//        registerUser(email, password, firstName, lastName, roles);
//    }

}
