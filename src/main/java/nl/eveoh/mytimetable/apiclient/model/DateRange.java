/*
 * Copyright 2013 - 2017 Eveoh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.eveoh.mytimetable.apiclient.model;

import com.google.common.base.MoreObjects;

import java.util.Date;

/**
 * DateRange used by {@link Term}s.
 *
 * @author Marco Krikke
 */
public class DateRange {

    private Date startDate;

    private Date endDate;


    private DateRange() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("startDate", startDate).add("endDate", endDate).toString();
    }
}
