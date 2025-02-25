/*
 * Copyright 2023 HM Revenue & Customs
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

package forms.aboutyou

import forms.behaviours.CheckboxFieldBehaviours
import models.aboutyou.CharitableDonations
import models.aboutyou.CharitableDonations.{GiftsOfLandOrProperty, NoDonations}
import play.api.data.FormError

class CharitableDonationsFormProviderSpec extends CheckboxFieldBehaviours {

  val form = new CharitableDonationsFormProvider()(false)
  val agentForm = new CharitableDonationsFormProvider()(true)

  ".value" - {

    val fieldName = "value"
    val requiredKey = "charitableDonations.error.required"
    val invalidErrorKey = "charitableDonations.error.invalid"

    behave like checkboxField[CharitableDonations](
      form,
      fieldName,
      validValues  = CharitableDonations.values,
      invalidError = FormError(s"$fieldName[0]", invalidErrorKey)
    )

    behave like mandatoryCheckboxField(
      form,
      fieldName,
      requiredKey
    )

    behave like exclusiveCheckboxField(
      form,
      NoDonations.toString,
      fieldName,
      GiftsOfLandOrProperty.toString,
      invalidErrorKey
    )
  }

  ".value for an agent" - {

    val fieldName = "value"
    val requiredKey = "charitableDonations.agent.error.required"
    val invalidErrorKey = "charitableDonations.agent.error.invalid"

    behave like mandatoryCheckboxField(
      agentForm,
      fieldName,
      requiredKey
    )

    behave like exclusiveCheckboxField(
      agentForm,
      NoDonations.toString,
      fieldName,
      GiftsOfLandOrProperty.toString,
      invalidErrorKey
    )
  }
}
