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

package controllers

import controllers.actions.TaxYearAction.taxYearAction
import controllers.actions._

import javax.inject.Inject
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, AnyContent, MessagesControllerComponents}
import uk.gov.hmrc.play.bootstrap.frontend.controller.FrontendBaseController
import views.html.{TaxReturnNotReadyAgentView, TaxReturnNotReadyView}

import scala.concurrent.ExecutionContext

class TaxReturnNotReadyController @Inject()(
                                       override val messagesApi: MessagesApi,
                                       identify: IdentifierActionProvider,
                                       getData: DataRetrievalActionProvider,
                                       val controllerComponents: MessagesControllerComponents,
                                       view: TaxReturnNotReadyView,
                                       agentView: TaxReturnNotReadyAgentView
                                     )(implicit ec: ExecutionContext) extends FrontendBaseController with I18nSupport {

  def onPageLoad(taxYear: Int): Action[AnyContent] = (identify(taxYear) andThen taxYearAction(taxYear) andThen getData(taxYear)) {
    implicit request =>
      if (request.isAgent) {
        Ok(agentView(taxYear))
      } else {
        Ok(view(taxYear))
      }
  }
}
