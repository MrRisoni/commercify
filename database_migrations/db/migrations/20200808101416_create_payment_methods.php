<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class CreatePaymentMethods extends AbstractMigration
{

    public function change(): void
    {
        $paymentMethods = $this->table('payment_methods', ['signed' => false]);
        $paymentMethods->addColumn('title', 'string', ['limit' => 55])
            ->create();
    }
}
