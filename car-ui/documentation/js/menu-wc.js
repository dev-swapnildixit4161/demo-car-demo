'use strict';

customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">java-competency-demo-ui documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                                <li class="link">
                                    <a href="properties.html" data-type="chapter-link">
                                        <span class="icon ion-ios-apps"></span>Properties
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-bs-toggle="collapse" ${ isNormalMode ?
                                'data-bs-target="#modules-links"' : 'data-bs-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse " ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link" >AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-AppModule-5c7724086161f5245c923d846e2197d63f5d8111b245f75a5db0b8137d60290dd646bdd445f6564d9cc5e65672d74fe9db43100d3a1e18c957c2154140020a83"' : 'data-bs-target="#xs-components-links-module-AppModule-5c7724086161f5245c923d846e2197d63f5d8111b245f75a5db0b8137d60290dd646bdd445f6564d9cc5e65672d74fe9db43100d3a1e18c957c2154140020a83"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-5c7724086161f5245c923d846e2197d63f5d8111b245f75a5db0b8137d60290dd646bdd445f6564d9cc5e65672d74fe9db43100d3a1e18c957c2154140020a83"' :
                                            'id="xs-components-links-module-AppModule-5c7724086161f5245c923d846e2197d63f5d8111b245f75a5db0b8137d60290dd646bdd445f6564d9cc5e65672d74fe9db43100d3a1e18c957c2154140020a83"' }>
                                            <li class="link">
                                                <a href="components/AppComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/AppRoutingModule.html" data-type="entity-link" >AppRoutingModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/CartModule.html" data-type="entity-link" >CartModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-CartModule-a8a1ea7d2681483c0b669cf32dae4a6a647b69219be24f71150a50354739359ca159cdd051b32bb2df90a5c893cf801523b481f89b6181849ab69465f2ff42db"' : 'data-bs-target="#xs-components-links-module-CartModule-a8a1ea7d2681483c0b669cf32dae4a6a647b69219be24f71150a50354739359ca159cdd051b32bb2df90a5c893cf801523b481f89b6181849ab69465f2ff42db"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-CartModule-a8a1ea7d2681483c0b669cf32dae4a6a647b69219be24f71150a50354739359ca159cdd051b32bb2df90a5c893cf801523b481f89b6181849ab69465f2ff42db"' :
                                            'id="xs-components-links-module-CartModule-a8a1ea7d2681483c0b669cf32dae4a6a647b69219be24f71150a50354739359ca159cdd051b32bb2df90a5c893cf801523b481f89b6181849ab69465f2ff42db"' }>
                                            <li class="link">
                                                <a href="components/CartComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CartComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CartItemsComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CartItemsComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/CartRoutingModule.html" data-type="entity-link" >CartRoutingModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/CloudOptionsModule.html" data-type="entity-link" >CloudOptionsModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-CloudOptionsModule-54dba6ab977c9e5db86010d84ddd62beca327a2659ecc0326326465f2c9fd3ee3c1f332a1e28093f4b3aed275ca34ca01bfabdcd4a8a910e23bc81edd1488d74"' : 'data-bs-target="#xs-components-links-module-CloudOptionsModule-54dba6ab977c9e5db86010d84ddd62beca327a2659ecc0326326465f2c9fd3ee3c1f332a1e28093f4b3aed275ca34ca01bfabdcd4a8a910e23bc81edd1488d74"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-CloudOptionsModule-54dba6ab977c9e5db86010d84ddd62beca327a2659ecc0326326465f2c9fd3ee3c1f332a1e28093f4b3aed275ca34ca01bfabdcd4a8a910e23bc81edd1488d74"' :
                                            'id="xs-components-links-module-CloudOptionsModule-54dba6ab977c9e5db86010d84ddd62beca327a2659ecc0326326465f2c9fd3ee3c1f332a1e28093f4b3aed275ca34ca01bfabdcd4a8a910e23bc81edd1488d74"' }>
                                            <li class="link">
                                                <a href="components/CarsDataComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CarsDataComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CloudOptionsComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CloudOptionsComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/TabBarComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >TabBarComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/CloudOptionsRoutingModule.html" data-type="entity-link" >CloudOptionsRoutingModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/DashboardModule.html" data-type="entity-link" >DashboardModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-DashboardModule-7da68fcd127dae3849461a85df75c0a7150b343a90f8fa7232344f5124703aff7dcdb73ffe11fcbfcc9fa4d918853aeb44321bb4cf2588d59bb6816105f0ee41"' : 'data-bs-target="#xs-components-links-module-DashboardModule-7da68fcd127dae3849461a85df75c0a7150b343a90f8fa7232344f5124703aff7dcdb73ffe11fcbfcc9fa4d918853aeb44321bb4cf2588d59bb6816105f0ee41"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-DashboardModule-7da68fcd127dae3849461a85df75c0a7150b343a90f8fa7232344f5124703aff7dcdb73ffe11fcbfcc9fa4d918853aeb44321bb4cf2588d59bb6816105f0ee41"' :
                                            'id="xs-components-links-module-DashboardModule-7da68fcd127dae3849461a85df75c0a7150b343a90f8fa7232344f5124703aff7dcdb73ffe11fcbfcc9fa4d918853aeb44321bb4cf2588d59bb6816105f0ee41"' }>
                                            <li class="link">
                                                <a href="components/ApiErrorComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ApiErrorComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ConfirmationDialogComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ConfirmationDialogComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/DashboardComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >DashboardComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/HeaderComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >HeaderComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ServerErrorComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ServerErrorComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SidenavComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SidenavComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/DashboardRoutingModule.html" data-type="entity-link" >DashboardRoutingModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/HomeModule.html" data-type="entity-link" >HomeModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-HomeModule-8c1add2077e300c652e2256e8d3460e96ad94ba5e8f05dd25bd820e881b259f3654275357a70d3b4ca5b647462f481e40a9b5af17c81058cd9db9c9dd6de3a82"' : 'data-bs-target="#xs-components-links-module-HomeModule-8c1add2077e300c652e2256e8d3460e96ad94ba5e8f05dd25bd820e881b259f3654275357a70d3b4ca5b647462f481e40a9b5af17c81058cd9db9c9dd6de3a82"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-HomeModule-8c1add2077e300c652e2256e8d3460e96ad94ba5e8f05dd25bd820e881b259f3654275357a70d3b4ca5b647462f481e40a9b5af17c81058cd9db9c9dd6de3a82"' :
                                            'id="xs-components-links-module-HomeModule-8c1add2077e300c652e2256e8d3460e96ad94ba5e8f05dd25bd820e881b259f3654275357a70d3b4ca5b647462f481e40a9b5af17c81058cd9db9c9dd6de3a82"' }>
                                            <li class="link">
                                                <a href="components/CarBrandsComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CarBrandsComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CarsListComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CarsListComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CarsdataCardComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CarsdataCardComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/DataNotFoundComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >DataNotFoundComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/HomeComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >HomeComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/PageNotFoundComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >PageNotFoundComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/HomeRoutingModule.html" data-type="entity-link" >HomeRoutingModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/MaterialModule.html" data-type="entity-link" >MaterialModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/SharedModule.html" data-type="entity-link" >SharedModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-SharedModule-43ae48b324c1d341a29eeacc7a03e0f9a271e1ae300d07151b81a483f0f2eebd56b482a195167020811ec01228bc2b527e75bdb32f38cf273458616e007d2319"' : 'data-bs-target="#xs-components-links-module-SharedModule-43ae48b324c1d341a29eeacc7a03e0f9a271e1ae300d07151b81a483f0f2eebd56b482a195167020811ec01228bc2b527e75bdb32f38cf273458616e007d2319"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-SharedModule-43ae48b324c1d341a29eeacc7a03e0f9a271e1ae300d07151b81a483f0f2eebd56b482a195167020811ec01228bc2b527e75bdb32f38cf273458616e007d2319"' :
                                            'id="xs-components-links-module-SharedModule-43ae48b324c1d341a29eeacc7a03e0f9a271e1ae300d07151b81a483f0f2eebd56b482a195167020811ec01228bc2b527e75bdb32f38cf273458616e007d2319"' }>
                                            <li class="link">
                                                <a href="components/ActionColumnComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ActionColumnComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/TableHeadersDropdownComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >TableHeadersDropdownComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/TabularViewComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >TabularViewComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                </ul>
                </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#components-links"' :
                            'data-bs-target="#xs-components-links"' }>
                            <span class="icon ion-md-cog"></span>
                            <span>Components</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="components-links"' : 'id="xs-components-links"' }>
                            <li class="link">
                                <a href="components/DataNotFoundComponent-1.html" data-type="entity-link" >DataNotFoundComponent</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#classes-links"' :
                            'data-bs-target="#xs-classes-links"' }>
                            <span class="icon ion-ios-paper"></span>
                            <span>Classes</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="classes-links"' : 'id="xs-classes-links"' }>
                            <li class="link">
                                <a href="classes/OrderSummary.html" data-type="entity-link" >OrderSummary</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#injectables-links"' :
                                'data-bs-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/CarDetailsService.html" data-type="entity-link" >CarDetailsService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/CarsListService.html" data-type="entity-link" >CarsListService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/CartService.html" data-type="entity-link" >CartService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/CommonService.html" data-type="entity-link" >CommonService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#interfaces-links"' :
                            'data-bs-target="#xs-interfaces-links"' }>
                            <span class="icon ion-md-information-circle-outline"></span>
                            <span>Interfaces</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"' }>
                            <li class="link">
                                <a href="interfaces/AvailableCloudOptions.html" data-type="entity-link" >AvailableCloudOptions</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/CarBrand.html" data-type="entity-link" >CarBrand</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/CarDetails.html" data-type="entity-link" >CarDetails</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/CarsData.html" data-type="entity-link" >CarsData</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/CartItem.html" data-type="entity-link" >CartItem</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#miscellaneous-links"'
                            : 'data-bs-target="#xs-miscellaneous-links"' }>
                            <span class="icon ion-ios-cube"></span>
                            <span>Miscellaneous</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"' }>
                            <li class="link">
                                <a href="miscellaneous/variables.html" data-type="entity-link">Variables</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <a data-type="chapter-link" href="routes.html"><span class="icon ion-ios-git-branch"></span>Routes</a>
                        </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank" rel="noopener noreferrer">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});