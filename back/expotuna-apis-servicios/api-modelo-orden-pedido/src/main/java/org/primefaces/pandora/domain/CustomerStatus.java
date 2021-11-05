/*
   Copyright 2009-2021 PrimeTek.

   Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.primefaces.pandora.domain;

import java.util.Random;

public enum CustomerStatus {
    QUALIFIED,
    UNQUALIFIED,
    NEGOTIATION,
    NEW,
    RENEWAL,
    PROPOSAL;

    public static CustomerStatus random() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
